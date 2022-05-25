package cl.arech.uicomponents.component

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import cl.arech.uicomponents.databinding.UiComponentProductCardBinding

data class AttrsProductCard(
    val imageUrl: String,
    val title: String,
    val value: String,
    val hasFreeShipping: Boolean = false,
)

class ProductCard @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: UiComponentProductCardBinding? = null

    init {
        if (binding == null) {
            val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = UiComponentProductCardBinding.inflate(inflater, this)
        }
    }

    fun setAttributes(attrs: AttrsProductCard) {
        setupImage(attrs.imageUrl)
        setTexts(attrs)
        setVisibilities(attrs)
    }

    private fun setupImage(imageUrl: String) = binding?.apply {

    }

    private fun setTexts(attrs: AttrsProductCard) = binding?.apply {
        productCardTitle.text = attrs.title
        productCardValue.text = attrs.value
    }

    private fun setVisibilities(attrs: AttrsProductCard) = binding?.apply {
        productCardFreeShipping.isVisible = attrs.hasFreeShipping
    }
}