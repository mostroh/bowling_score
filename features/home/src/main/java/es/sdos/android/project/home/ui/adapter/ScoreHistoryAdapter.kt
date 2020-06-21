package es.sdos.android.project.home.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import es.sdos.android.project.common.extension.getLayoutInflater
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.feature.home.databinding.RowScoresBinding

class ScoreHistoryAdapter : RecyclerView.Adapter<ScoreHistoryViewHolder>() {

    private val gameList: MutableList<GameBo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreHistoryViewHolder =
        ScoreHistoryViewHolder(RowScoresBinding.inflate(parent.getLayoutInflater(), parent, false))

    override fun getItemCount(): Int =
        gameList.size

    override fun onBindViewHolder(holder: ScoreHistoryViewHolder, position: Int) =
        holder.bind(gameList[position])

    fun updateData(items: List<GameBo>) {
        val diffCallback = ScoreHistoryDiffCallback(gameList, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        gameList.clear()
        gameList.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

}

class ScoreHistoryViewHolder(private val binding: RowScoresBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(game: GameBo) {
        binding.item = game
    }

}

class ScoreHistoryDiffCallback(private val oldList: List<GameBo>, private val newList: List<GameBo>) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}
