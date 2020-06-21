package es.sdos.android.project.home.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import es.sdos.android.project.data.model.game.RoundBo
import es.sdos.android.project.home.ui.adapter.RoundAdapter

object GameBinding {

    fun shotScore(round: RoundBo, shotNumber: Int): String {
        return when (shotNumber) {
            1 -> {
                when (round.firstShot) {
                    0 -> "-"
                    10 -> "X"
                    else -> round.firstShot.toString()
                }
            }
            2 -> {
                round.secondShot?.let { secondShot ->
                    when {
                        secondShot == 0 -> "-"
                        secondShot + round.firstShot == 10 -> "/"
                        else -> secondShot.toString()
                    }
                } ?: ""
            }
            else -> {
                round.thirdShot?.let { thirdShot ->
                    when (thirdShot) {
                        0 -> "-"
                        10 -> "X"
                        else -> thirdShot.toString()
                    }
                } ?: ""
            }
        }
    }

    fun intOrEmpty(value: Int?) : String {
        return value?.toString() ?: ""
    }

    @BindingAdapter("app:rounds")
    @JvmStatic
    fun rounds(view: RecyclerView, rounds: List<RoundBo>?) {
        if (view.adapter !is RoundAdapter) {
            view.adapter = RoundAdapter()
        }

        rounds?.let { (view.adapter as RoundAdapter).updateData(it) }
    }


}
