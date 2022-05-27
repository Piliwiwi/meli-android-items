package cl.arech.uicomponents.template

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import cl.arech.uicomponents.databinding.UiTemplateErrorBinding

data class AttrsErrorTemplate(
    val title: String,
    val description: String,
)

class ErrorTemplate @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: UiTemplateErrorBinding? = null

    init {
        if (binding == null) {
            val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = UiTemplateErrorBinding.inflate(inflater, this)
        }

        initAttributes()
    }

    private fun initAttributes() = binding?.errorTemplateIcon?.apply {
        setFailureListener { Log.e("ANIMATION ERROR", it.toString()) }
        setAnimation("animations/$WARNING_RED")
    }

    fun setAttributes(attrs: AttrsErrorTemplate) {
        reproduceAnimation()
        setTexts(attrs)
    }

    private fun reproduceAnimation() = binding?.errorTemplateIcon?.apply {
        playAnimation()
    }

    private fun setTexts(attrs: AttrsErrorTemplate) = binding?.apply {
        errorTemplateTitle.text = attrs.title
        errorTemplateDescription.text = attrs.description
    }

    companion object {
        const val WARNING_RED = "warning_red.json"
    }
}