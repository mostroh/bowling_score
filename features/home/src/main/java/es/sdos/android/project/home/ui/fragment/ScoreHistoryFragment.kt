package es.sdos.android.project.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.sdos.android.project.common.di.ViewModelFactory
import es.sdos.android.project.common.ui.BaseFragment
import es.sdos.android.project.common.ui.BaseViewModel
import es.sdos.android.project.feature.home.databinding.FragmentScoresBinding
import es.sdos.android.project.home.ui.viewmodel.ScoreHistoryViewModel
import javax.inject.Inject

class ScoreHistoryFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ScoreHistoryViewModel>
    private val viewModel: ScoreHistoryViewModel by lazy { viewModelFactory.get() }


    private lateinit var binding: FragmentScoresBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentScoresBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun getViewModel() = viewModel as BaseViewModel

}