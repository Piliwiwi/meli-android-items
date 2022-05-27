package cl.arech.uicomponents.groupcomponent.productcardlist.adapter

import androidx.recyclerview.widget.RecyclerView
import cl.arech.uicomponents.component.AttrsProductCard
import cl.arech.uicomponents.databinding.UiItemProductCardBinding

class ProductCardListViewHolder(
    private val binding: UiItemProductCardBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(attrs: AttrsProductCard) = binding.apply {
        itemProductCard.setAttributes(attrs)
    }
}