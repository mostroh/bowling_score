package es.sdos.android.project.launcher.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import es.sdos.android.project.common.di.ViewModelFactory
import es.sdos.android.project.common.ui.BaseFragment
import es.sdos.android.project.common.ui.BaseViewModel
import es.sdos.android.project.data.repository.util.AsyncResult
import es.sdos.android.project.feature.launcher.databinding.FragmentLauncherBinding
import es.sdos.android.project.launcher.ui.viewmodel.LauncherViewModel
import javax.inject.Inject

class LauncherFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<LauncherViewModel>
    private val viewModel: LauncherViewModel by lazy { viewModelFactory.get() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLauncherBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSynchronizationStatus().observe(viewLifecycleOwner, Observer {
            if (it.status == AsyncResult.Status.SUCCESS) {
                viewModel.synchronizationFinish()
            }
        })
    }

    override fun getViewModel() = viewModel as BaseViewModel

}