package cl.arech.awesomeitems.products.ui.navigator

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import cl.arech.awesomeitems.products.ui.list.ListFragmentDirections
import cl.arech.awesomeitems.products.ui.search.SearchFragmentDirections
import javax.inject.Inject

class ProductsNavigator @Inject constructor() {
    fun navigateFromSearchToList(view: View?, query: String) = view?.apply {
        val direction = SearchFragmentDirections.fromSearchToList(query)
        safeNavigation(this, direction)
    }

    fun navigateFromListToDetails(view: View?, productId: String) = view?.apply {
        val direction = ListFragmentDirections.fromListToDetails(productId)
        safeNavigation(this, direction)
    }

    private fun safeNavigation(view: View, direction: NavDirections) {
        view.findNavController().currentDestination?.getAction(direction.actionId)
            ?.let { view.findNavController().navigate(direction) }
    }
}