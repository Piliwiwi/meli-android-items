package cl.arech.uicomponents.groupcomponent.productcardlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.arech.uicomponents.databinding.UiItemProductCardBinding
import cl.arech.uicomponents.groupcomponent.productcardlist.AttrsProductCardList

class ProductCardListAdapter(private val attrs: AttrsProductCardList) :
    RecyclerView.Adapter<ProductCardListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductCardListViewHolder(
        UiItemProductCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductCardListViewHolder, position: Int) {
        holder.bind(attrs.items[position])
    }

    override fun getItemCount() = attrs.items.size
}