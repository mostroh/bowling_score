package es.sdos.android.project.common.ui.binding

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import es.sdos.android.project.common.extension.setInvisible
import es.sdos.android.project.common.extension.setVisible
import es.sdos.android.project.data.repository.util.AsyncResult
import es.sdos.android.project.data.repository.util.AsyncResult.Status.LOADING

object CommonBinding {

    @BindingAdapter("app:refreshAsyncResult")
    @JvmStatic
    fun <T> refreshAsyncResult(view: SwipeRefreshLayout, asyncResult: AsyncResult<T>?) {
        view.isRefreshing = asyncResult?.status == LOADING
    }

    @BindingAdapter("app:showWhenLoading")
    @JvmStatic
    fun <T> showWhenLoading(view: View, asyncResult: AsyncResult<T>?) {
        view.setVisible(asyncResult?.status == LOADING)
    }

    @BindingAdapter("app:hideWhenLoading")
    @JvmStatic
    fun <T> hideWhenLoading(view: View, asyncResult: AsyncResult<T>?) {
        view.setInvisible(asyncResult?.status == LOADING)
    }

    @BindingAdapter("app:isVisible")
    @JvmStatic
    fun isVisible(view: View, visible: Boolean) {
        view.isVisible = visible
    }

}
