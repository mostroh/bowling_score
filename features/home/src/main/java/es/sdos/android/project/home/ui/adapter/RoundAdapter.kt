package es.sdos.android.project.home.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import es.sdos.android.project.common.extension.getLayoutInflater
import es.sdos.android.project.data.model.game.RoundBo
import es.sdos.android.project.feature.home.databinding.RowRoundBinding

class RoundAdapter : RecyclerView.Adapter<RoundViewHolder>() {

    private val roundList: MutableList<RoundBo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoundViewHolder =
        RoundViewHolder(RowRoundBinding.inflate(parent.getLayoutInflater(), parent, false))

    override fun getItemCount(): Int =
        roundList.size

    override fun onBindViewHolder(holder: RoundViewHolder, position: Int) =
        holder.bind(roundList[position])

    fun updateData(items: List<RoundBo>) {
        val diffCallback = RoundDiffCallback(roundList, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        roundList.clear()
        roundList.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

}

class RoundViewHolder(private val binding: RowRoundBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(round: RoundBo) {
        binding.item = round
    }

}

class RoundDiffCallback(private val oldList: List<RoundBo>, private val newList: List<RoundBo>) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}
