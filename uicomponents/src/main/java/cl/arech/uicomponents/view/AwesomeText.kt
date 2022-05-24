package cl.arech.uicomponents.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import cl.arech.uicomponents.R

class AwesomeText @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        initAttributes()
    }

    private fun initAttributes() {
        val styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.AwesomeText)

        try {
            when (styledAttributes.getInt(R.styleable.AwesomeText_ui_text_type, TITLE)) {
                TITLE -> TextViewCompat.setTextAppearance(
                    this,
                    R.style.UiFontTitle
                )
                VALUE -> TextViewCompat.setTextAppearance(
                    this,
                    R.style.UiFontValue
                )
                INFO -> TextViewCompat.setTextAppearance(
                    this,
                    R.style.UiFontInfo
                )
                HIGHLIGHT_INFO -> TextViewCompat.setTextAppearance(
                    this,
                    R.style.UiFontHighlightInfo
                )
                SUBTITLE -> TextViewCompat.setTextAppearance(
                    this,
                    R.style.UiFontSubtitle
                )
                ADDITIONAL_INFO -> TextViewCompat.setTextAppearance(
                    this,
                    R.style.UiFontAdditionalInfo
                )
            }
        } finally {
            styledAttributes.recycle()
        }
    }

    companion object {
        const val TITLE = 1
        const val VALUE = 2
        const val INFO = 3
        const val HIGHLIGHT_INFO = 4
        const val SUBTITLE = 5
        const val ADDITIONAL_INFO = 6
    }
}