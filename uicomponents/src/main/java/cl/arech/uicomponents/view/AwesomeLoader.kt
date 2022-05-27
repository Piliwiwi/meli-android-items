package cl.arech.uicomponents.view

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import cl.arech.uicomponents.databinding.UiViewAwesomeLoaderBinding

class AwesomeLoader @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private var binding: UiViewAwesomeLoaderBinding? = null

    init {
        if (binding == null) {
            val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = UiViewAwesomeLoaderBinding.inflate(inflater, this)
        }

        initAttrs()
    }

    private fun initAttrs() = binding?.loadingView?.apply {
        setFailureListener { Log.e("ANIMATION ERROR", it.toString()) }
        setAnimation("animations/$SEARCH_PRODUCT")
    }

    companion object {
        const val SEARCH_PRODUCT = "search_product_loader.json"
    }
}