package cl.arech.awesomeitems.products.presentation.list

import cl.arech.mvi.events.MviUserIntent

sealed class ListUIntent : MviUserIntent {
    data class SearchProductsInitialUIntent(val query: String) : ListUIntent()
    data class SearchAnotherProductsUIntent(val query: String) : ListUIntent()
}