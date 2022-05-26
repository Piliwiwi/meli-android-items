package cl.arech.awesomeitems.products.presentation.list

import cl.arech.awesomeitems.products.data.ProductsDataRepository
import cl.arech.awesomeitems.products.data.remote.model.RemoteProducts
import cl.arech.awesomeitems.products.factory.ProductFactory.makeProducts
import cl.arech.awesomeitems.products.factory.ProductFactory.makeRemoteProducts
import cl.arech.awesomeitems.products.presentation.list.ListAction.LoadProductsAction
import cl.arech.awesomeitems.products.presentation.list.ListResult.LoadProductsResult.Error
import cl.arech.awesomeitems.products.presentation.list.ListResult.LoadProductsResult.InProgress
import cl.arech.awesomeitems.products.presentation.list.ListResult.LoadProductsResult.Success
import cl.arech.awesomeitems.products.presentation.list.mapper.ProductsMapper
import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.mvi.execution.ExecutionThreadEnvironment
import cl.arech.mvi.execution.ExecutionThreadFactory
import cl.arech.testingtools.factory.RandomFactory.generateString
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue

@FlowPreview
internal class ListProcessorTest {
    private val repository = mockk<ProductsDataRepository>()
    private val mapper = mockk<ProductsMapper>()
    private val executionThread = ExecutionThreadFactory.makeExecutionThread(
        ExecutionThreadEnvironment.TESTING
    )

    private val processor = ListProcessor(
        repository = repository,
        mapper = mapper,
        executionThread = executionThread
    )

    @Test
    fun `given LoadProductsAction, when actionProcessor, then InProgress`() = runBlocking {
        val query = generateString()
        val remoteResponse = makeRemoteProducts(6)
        val response = makeProducts(6)

        stubMapper(remoteResponse, response)
        stubRepositorySearchProductsSuccess(query, remoteResponse)

        val result = processor.actionProcessor(LoadProductsAction(query)).toList()

        val mapOutput = result.first()

        assertTrue(mapOutput is InProgress)
    }

    @Test
    fun `given LoadProductsAction, when actionProcessor, then Success`() = runBlocking {
        val query = generateString()
        val remoteResponse = makeRemoteProducts(6)
        val response = makeProducts(6)

        stubMapper(remoteResponse, response)
        stubRepositorySearchProductsSuccess(query, remoteResponse)

        val result = processor.actionProcessor(LoadProductsAction(query)).toList()

        val mapOutput = result.last()

        assertTrue(mapOutput is Success)
    }

    @Test
    fun `given LoadProductsAction, when actionProcessor, then Error`() = runBlocking {
        val query = generateString()
        val error = Throwable()

        stubRepositorySearchProductsFailed(query, error)

        val result = processor.actionProcessor(LoadProductsAction(query)).toList()

        val mapOutput = result.last()

        assertTrue(mapOutput is Error)
    }

    private fun stubMapper(remote: RemoteProducts, presentation: Products) {
        every { with(mapper) { remote.toPresentation() } } returns presentation
    }

    private fun stubRepositorySearchProductsSuccess(
        query: String,
        response: RemoteProducts,
    ) = runBlocking {
        coEvery { repository.searchProducts(query) } returns flow { emit(response) }
    }

    private fun stubRepositorySearchProductsFailed(query: String, cause: Throwable) = runBlocking {
        coEvery { repository.searchProducts(query) } throws cause
    }
}