package cl.arech.awesomeitems.products.presentation.list

import cl.arech.awesomeitems.products.factory.ProductFactory.makeProducts
import cl.arech.awesomeitems.products.presentation.list.ListResult.LoadProductsResult.Error
import cl.arech.awesomeitems.products.presentation.list.ListResult.LoadProductsResult.InProgress
import cl.arech.awesomeitems.products.presentation.list.ListResult.LoadProductsResult.Success
import cl.arech.awesomeitems.products.presentation.list.ListUiState.DefaultUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.ErrorUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.LoadingUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.ShowProductsUiState
import cl.arech.mvi.exception.UnsupportedReduceException
import org.junit.Test

internal class ListReducerTest {
    private val reducer = ListReducer()

    @Test
    fun `given DefaultUiState, when InProgress, then LoadingUiState`() {
        val currentState = DefaultUiState
        val result = InProgress

        val nextState = with(reducer) { currentState reduceWith result }

        assert(nextState is LoadingUiState)
    }

    @Test
    fun `given LoadingUiState, when Error, then ErrorUiState`() {
        val currentState = LoadingUiState
        val result = Error

        val nextState = with(reducer) { currentState reduceWith result }

        assert(nextState is ErrorUiState)
    }

    @Test
    fun `given LoadingUiState, when Success, then ShowProductsUiState`() {
        val products = makeProducts(6)
        val currentState = LoadingUiState
        val result = Success(products)

        val nextState = with(reducer) { currentState reduceWith result }

        assert(nextState is ShowProductsUiState)
    }

    @Test
    fun `given ErrorUiState, when InProgress, then LoadingUiState`() {
        val currentState = ErrorUiState
        val result = InProgress

        val nextState = with(reducer) { currentState reduceWith result }

        assert(nextState is LoadingUiState)
    }

    @Test
    fun `given ShowProductsUiState, when InProgress, then LoadingUiState`() {
        val products = makeProducts(6)
        val currentState = ShowProductsUiState(products)
        val result = InProgress

        val nextState = with(reducer) { currentState reduceWith result }

        assert(nextState is LoadingUiState)
    }

    @Test(expected = UnsupportedReduceException::class)
    fun `given ShowProductsUiState, when non-InProgress, then throw exception`() {
        val products = makeProducts(6)
        val previousState = ShowProductsUiState(products)
        val result = Error

        with(reducer) { previousState reduceWith result }
    }

    @Test(expected = UnsupportedReduceException::class)
    fun `given DefaultUiState, when non-InProgress, then throw exception`() {
        val previousState = DefaultUiState
        val result = Error

        with(reducer) { previousState reduceWith result }
    }

    @Test(expected = UnsupportedReduceException::class)
    fun `given LoadingUiState, when InProgress, then throw exception`() {
        val previousState = LoadingUiState
        val result = InProgress

        with(reducer) { previousState reduceWith result }
    }

    @Test(expected = UnsupportedReduceException::class)
    fun `given ErrorUiState, when Error, then throw exception`() {
        val previousState = ErrorUiState
        val result = Error

        with(reducer) { previousState reduceWith result }
    }
}