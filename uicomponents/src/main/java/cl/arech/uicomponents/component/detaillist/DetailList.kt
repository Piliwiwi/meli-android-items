package cl.arech.uicomponents.component.detaillist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import cl.arech.uicomponents.component.detaillist.adapter.DetailListAdapter
import cl.arech.uicomponents.databinding.UiComponentDetailListBinding

data class AttrsDetail(
    val title: String,
    val value: String,
)

class DetailList @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: UiComponentDetailListBinding? = null

    init {
        if (binding == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = UiComponentDetailListBinding.inflate(inflater, this)
        }
    }

    fun setAttributes(attrs: List<AttrsDetail>) {
        setRecyclerView(attrs)
    }

    private fun setRecyclerView(attrs: List<AttrsDetail>) = binding?.apply {
        val adapter = DetailListAdapter(attrs)
        detailListRecyclerview.adapter = adapter
    }
}