package es.sdos.android.project.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import es.sdos.android.project.common.di.ViewModelFactory
import es.sdos.android.project.common.ui.BaseFragment
import es.sdos.android.project.common.ui.BaseViewModel
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.repository.util.AsyncResult
import es.sdos.android.project.feature.home.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPendingGameLiveData().observe(viewLifecycleOwner, Observer { result ->
            if (!result.data.isNullOrEmpty()){
                pendingGame = result.data?.first().takeIf { result.status == AsyncResult.Status.SUCCESS }
            }
        })
    }

    private fun bindClicks() {
        binding.homeNewGame.setOnClickListener {
            showPendingGameAlert()
        }
        binding.homeContinueGame.setOnClickListener {
            onContinueGameClick()
        }
    }

    private fun showPendingGameAlert(){
        activity?.let {
            val builder = AlertDialog.Builder(it)
            with(builder)
            {
                setTitle(R.string.warning)
                setMessage(R.string.delete_game_alert)
                setPositiveButton(android.R.string.yes){ _, _ -> onNewGameClick() }
                setNegativeButton(android.R.string.no) {dialog, _ ->  dialog.dismiss() }
                show()
            }
        }
    }

    private fun onContinueGameClick() {
        pendingGame?.id?.let { viewModel.goToGame(it) }
    }

    private fun onNewGameClick() {
        pendingGame?.id?.let { viewModel.deleteGame(it) }

        viewModel.createGame().observe(viewLifecycleOwner, Observer { result ->
            binding.homeNewGame.isEnabled = result.status != AsyncResult.Status.LOADING
            result.data?.takeIf { result.status == AsyncResult.Status.SUCCESS }?.id?.let { viewModel.goToGame(it) }
        })
    }

    override fun getViewModel() = viewModel as BaseViewModel

}