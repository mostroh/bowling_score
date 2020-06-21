package es.sdos.android.project.launcher.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.repository.util.AsyncResult
import es.sdos.android.project.feature.launcher.R

object LauncherBinding {

    @BindingAdapter("app:synchronizeText")
    @JvmStatic
    fun synchronizeText(textView: TextView, asyncResult: AsyncResult<List<GameBo>>?) {
        val isFinished = asyncResult?.status == AsyncResult.Status.SUCCESS
        textView.setText(
            if (isFinished) {
                R.string.synchronize_data_success
            } else {
                R.string.synchronize_data_loading
            }
        )
    }

}