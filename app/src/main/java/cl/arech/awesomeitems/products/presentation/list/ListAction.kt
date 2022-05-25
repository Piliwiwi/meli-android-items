package cl.arech.awesomeitems.products.presentation.list

import cl.arech.mvi.events.MviAction

sealed class ListAction : MviAction {
    data class LoadProductsAction(val query: String) : ListAction()
}