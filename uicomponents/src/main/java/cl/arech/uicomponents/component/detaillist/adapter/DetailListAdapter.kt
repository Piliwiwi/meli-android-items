package cl.arech.uicomponents.component.detaillist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.arech.uicomponents.component.detaillist.AttrsDetail
import cl.arech.uicomponents.databinding.UiItemDetailCardBinding

class DetailListAdapter(private val details: List<AttrsDetail>) :
    RecyclerView.Adapter<DetailListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DetailListViewHolder(
        UiItemDetailCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: DetailListViewHolder, position: Int) {
        holder.bind(details[position])
    }

    override fun getItemCount() = details.size
}