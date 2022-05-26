package cl.arech.awesomeitems.products.presentation.list

import cl.arech.awesomeitems.products.presentation.list.ListResult.LoadProductsResult.Error
import cl.arech.awesomeitems.products.presentation.list.ListResult.LoadProductsResult.InProgress
import cl.arech.awesomeitems.products.presentation.list.ListResult.LoadProductsResult.Success
import cl.arech.awesomeitems.products.presentation.list.ListUiState.DefaultUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.ErrorUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.LoadingUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.ShowProductsUiState
import cl.arech.mvi.MviReducer
import cl.arech.mvi.exception.UnsupportedReduceException
import javax.inject.Inject

class ListReducer @Inject constructor() : MviReducer<ListUiState, ListResult> {
    override fun ListUiState.reduceWith(result: ListResult): ListUiState {
        return when (val currentState = this) {
            is DefaultUiState -> currentState reduceWith result
            is LoadingUiState -> currentState reduceWith result
            is ErrorUiState -> currentState reduceWith result
            is ShowProductsUiState -> currentState reduceWith result
        }
    }

    private infix fun DefaultUiState.reduceWith(result: ListResult): ListUiState {
        return when (result) {
            InProgress -> LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun LoadingUiState.reduceWith(result: ListResult): ListUiState {
        return when (result) {
            Error -> ErrorUiState
            is Success -> ShowProductsUiState(result.products)
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun ErrorUiState.reduceWith(result: ListResult): ListUiState {
        return when (result) {
            InProgress -> LoadingUiState
            else -> throw UnsupportedReduceException(this, result)
        }
    }

    private infix fun ShowProductsUiState.reduceWith(result: ListResult): ListUiState {
        throw UnsupportedReduceException(this, result)
    }
}