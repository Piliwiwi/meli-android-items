package cl.arech.awesomeitems.products.presentation.list

import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.mvi.events.MviUiState

sealed class ListUiState : MviUiState {
    object DefaultUiState : ListUiState()
    object LoadingUiState : ListUiState()
    object ErrorUiState : ListUiState()
    object EmptySearchUiState : ListUiState()
    data class ShowProductsUiState(val products: Products) : ListUiState()
}
