package cl.arech.awesomeitems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.arech.awesomeitems.products.ui.ProductsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToProducts()
    }

    private fun navigateToProducts() {
        val intent = ProductsActivity.makeIntent(this)
        startActivity(intent)
    }
}