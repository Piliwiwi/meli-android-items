package cl.arech.awesomeitems

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cl.arech.awesomeitems.databinding.ActivityMainBinding
import cl.arech.awesomeitems.products.ui.ProductsActivity
import cl.arech.uicomponents.template.InfoTemplate
import cl.arech.uicomponents.view.AwesomeLoader
import cl.arech.utils.extension.onAnimationEnd
import com.airbnb.lottie.LottieDrawable

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (binding == null) binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onStart() {
        super.onStart()
        setupSplash()
    }

    private fun setupSplash() = binding?.lottieAnimation?.apply {
        setFailureListener { Log.e("ANIMATION ERROR", it.toString()) }
        setAnimation("animations/$AWESOME_LOTTIE_JSON")
        onAnimationEnd {
            navigateToProducts()
        }
    }

    private fun navigateToProducts() {
        val intent = ProductsActivity.makeIntent(this)
        startActivity(intent)
    }

    companion object {
        const val AWESOME_LOTTIE_JSON = "awesome_lottie.json"
    }
}