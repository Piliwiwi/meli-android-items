package cl.arech.awesomeitems.products.presentation.list

import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.mvi.events.MviResult

sealed class ListResult : MviResult {
    sealed class LoadProductsResult : ListResult() {
        object InProgress : LoadProductsResult()
        object Error : LoadProductsResult()
        object Empty : LoadProductsResult()
        data class Success(val products: Products) : LoadProductsResult()
    }
}