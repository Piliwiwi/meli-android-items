package cl.arech.uicomponents.component

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.SearchView.OnQueryTextListener
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import cl.arech.uicomponents.databinding.UiComponentAwesomeSearchBinding

data class AttrsAwesomeSearch(
    @StringRes val hint: Int? = null,
    val onSubmit: (String) -> Unit = {},
    val onChange: (String) -> Unit = {},
)

class AwesomeSearch @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: UiComponentAwesomeSearchBinding? = null

    init {
        if (binding == null) {
            val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = UiComponentAwesomeSearchBinding.inflate(inflater, this)
        }
    }

    fun setAttributes(attrs: AttrsAwesomeSearch) {
        setTexts(attrs)
        setSearchEvents(attrs)
    }

    private fun setTexts(attrs: AttrsAwesomeSearch) = binding?.apply {
        attrs.hint?.let { awesomeSearchView.queryHint = context.getString(it) }
    }

    private fun setSearchEvents(attrs: AttrsAwesomeSearch) = binding?.apply {
        awesomeSearchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) attrs.onSubmit(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                attrs.onChange(query.orEmpty())
                return false
            }
        })
    }
}