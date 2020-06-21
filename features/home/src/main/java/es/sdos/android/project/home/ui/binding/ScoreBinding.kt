package es.sdos.android.project.home.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.repository.util.AsyncResult
import es.sdos.android.project.home.ui.adapter.ScoreHistoryAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object ScoreBinding {

    @BindingAdapter("app:scoreHistoryItems")
    @JvmStatic
    fun scoreHistoryItems(view: RecyclerView, hours: AsyncResult<List<GameBo>>?) {
        if (view.adapter !is ScoreHistoryAdapter) {
            view.adapter = ScoreHistoryAdapter()
        }

        hours?.data?.let {
            (view.adapter as ScoreHistoryAdapter).updateData(it)
        }
    }

    @BindingAdapter("app:date")
    @JvmStatic
    fun date(view: TextView, date: Date) {
        try {
            val outDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            view.text = outDateFormat.format(date)
        } catch (e: Exception) {
            view.text = ""
        }
    }

}
