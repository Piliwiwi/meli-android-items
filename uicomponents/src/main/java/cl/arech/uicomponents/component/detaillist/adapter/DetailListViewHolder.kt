package cl.arech.uicomponents.component.detaillist.adapter

import androidx.recyclerview.widget.RecyclerView
import cl.arech.uicomponents.component.detaillist.AttrsDetail
import cl.arech.uicomponents.databinding.UiItemDetailCardBinding

class DetailListViewHolder(
    private val binding: UiItemDetailCardBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(detail: AttrsDetail) = binding.apply {
        itemDetailTitle.text = detail.title
        itemDetailValue.text = detail.value
    }
}