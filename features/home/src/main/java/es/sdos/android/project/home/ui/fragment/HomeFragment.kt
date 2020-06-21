package es.sdos.android.project.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import es.sdos.android.project.common.di.ViewModelFactory
import es.sdos.android.project.common.ui.BaseFragment
import es.sdos.android.project.common.ui.BaseViewModel
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.repository.util.AsyncResult
import es.sdos.android.project.feature.home.databinding.FragmentHomeBinding
import es.sdos.android.project.home.ui.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<HomeViewModel>
    private val viewModel: HomeViewModel by lazy { viewModelFactory.get() }

    private lateinit var binding: FragmentHomeBinding
    private var pendingGame: GameBo? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        bindClicks()
        return binding.root
    }

    private fun bindClicks() {
        binding.homeNewGame.setOnClickListener {
            onNewGameClick()
        }
        binding.homeContinueGame.setOnClickListener {
            onContinueGameClick()
        }
    }

    private fun onContinueGameClick() {
        pendingGame?.id?.let { viewModel.goToGame(it) }
    }

    private fun onNewGameClick() {
        viewModel.createGame().observe(viewLifecycleOwner, Observer { result ->
            binding.homeNewGame.isEnabled = result.status != AsyncResult.Status.LOADING
            result.data?.takeIf { result.status == AsyncResult.Status.SUCCESS }?.id?.let { viewModel.goToGame(it) }
        })
    }

    override fun getViewModel() = viewModel as BaseViewModel

}