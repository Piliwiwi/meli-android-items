package cl.arech.uicomponents.template

import android.animation.Animator
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import cl.arech.uicomponents.R
import cl.arech.uicomponents.databinding.UiTemplateInfoBinding
import cl.arech.utils.extension.onAnimationEnd
import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.LottieDrawable.INFINITE

data class AttrsInfoTemplate(
    val title: String,
    val description: String,
    val loop: Boolean = false,
)

class InfoTemplate @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: UiTemplateInfoBinding? = null

    init {
        if (binding == null) {
            val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = UiTemplateInfoBinding.inflate(inflater, this)
        }

        initAttributes()
    }

    private fun initAttributes() {
        val styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.InfoTemplate)
        val loader = styledAttributes.getInt(R.styleable.InfoTemplate_ui_lottie_type, WARNING_RED)

        try {
            binding?.infoTemplateIcon?.apply {
                setFailureListener { Log.e("ANIMATION ERROR", it.toString()) }
                setAnimation(
                    "animations/${
                        when (loader) {
                            WARNING_RED -> "warning_red.json"
                            EMPTY_SEARCH -> "empty_search.json"
                            else -> "warning_red.json"
                        }
                    }"
                )
            }
        } finally {
            styledAttributes.recycle()
        }
    }

    fun setAttributes(attrs: AttrsInfoTemplate) {
        reproduceAnimation(attrs)
        setTexts(attrs)
    }

    private fun reproduceAnimation(attrs: AttrsInfoTemplate) = binding?.infoTemplateIcon?.apply {
        playAnimation()
        onAnimationEnd {
            if (attrs.loop) {
                setMinFrame(MIN_FRAME_LOOP)
                repeatCount = INFINITE
                playAnimation()
            }
        }
    }

    private fun setTexts(attrs: AttrsInfoTemplate) = binding?.apply {
        infoTemplateTitle.text = attrs.title
        infoTemplateDescription.text = attrs.description
    }

    companion object {
        const val WARNING_RED = 1
        const val EMPTY_SEARCH = 2
        const val MIN_FRAME_LOOP = 150
    }
}