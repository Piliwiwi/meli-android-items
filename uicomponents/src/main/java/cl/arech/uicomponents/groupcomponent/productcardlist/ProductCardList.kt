package cl.arech.uicomponents.groupcomponent.productcardlist

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import cl.arech.uicomponents.component.AttrsProductCard
import cl.arech.uicomponents.databinding.UiGroupComponentProductCardListBinding

data class AttrsProductCardList(
    val items: List<AttrsProductCard>,
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
}