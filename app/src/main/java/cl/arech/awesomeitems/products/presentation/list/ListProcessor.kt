package cl.arech.awesomeitems.products.presentation.list

import cl.arech.awesomeitems.products.data.ProductsDataRepository
import cl.arech.awesomeitems.products.presentation.list.ListAction.LoadProductsAction
import cl.arech.awesomeitems.products.presentation.list.ListResult.LoadProductsResult.Empty
import cl.arech.awesomeitems.products.presentation.list.ListResult.LoadProductsResult.Error
import cl.arech.awesomeitems.products.presentation.list.ListResult.LoadProductsResult.InProgress
import cl.arech.awesomeitems.products.presentation.list.ListResult.LoadProductsResult.Success
import cl.arech.awesomeitems.products.presentation.list.mapper.ProductsMapper
import cl.arech.mvi.execution.ExecutionThread
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@FlowPreview
class ListProcessor @Inject constructor(
    private val repository: ProductsDataRepository,
    private val mapper: ProductsMapper,
    private val executionThread: ExecutionThread,
) {
    fun actionProcessor(action: ListAction): Flow<ListResult> =
        when (action) {
            is LoadProductsAction -> loadProductsProcessor(action.query)
        }

    private fun loadProductsProcessor(query: String) = flow<ListResult> {
        repository.searchProducts(query).collect { remoteProducts ->
            val products = with(mapper) { remoteProducts.toPresentation() }
            emit(if (products.results.isEmpty()) Empty else Success(products))
        }
    }.onStart {
        emit(InProgress)
    }.catch {
        emit(Error)
    }.flowOn(executionThread.ioThread())
}