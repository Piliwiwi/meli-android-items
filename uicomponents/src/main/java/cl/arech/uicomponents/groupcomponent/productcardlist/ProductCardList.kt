package cl.arech.uicomponents.groupcomponent.productcardlist

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import cl.arech.uicomponents.component.AttrsProductCard
import cl.arech.uicomponents.databinding.UiGroupComponentProductCardListBinding
import cl.arech.uicomponents.groupcomponent.productcardlist.adapter.ProductCardListAdapter
import cl.arech.utils.extension.onScrollEnd

data class AttrsProductCardList(
    val items: List<AttrsProductCard>,
    val onClick: (String) -> Unit = {},
    val onScrollEnd: () -> Unit = {},
)

class ProductCardList @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: UiGroupComponentProductCardListBinding? = null

    init {
        if (binding == null) {
            val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = UiGroupComponentProductCardListBinding.inflate(inflater, this)
        }
    }

    fun setAttributes(attrs: AttrsProductCardList) {
        setupList(attrs)
        setEvents(attrs)
    }

    private fun setupList(attrs: AttrsProductCardList) = binding?.apply {
        val adapter = ProductCardListAdapter(attrs)
        productCardRecyclerview.adapter = adapter
    }

    private fun setEvents(attrs: AttrsProductCardList) = binding?.apply {
        productCardRecyclerview.onScrollEnd { attrs.onScrollEnd() }
    }
}