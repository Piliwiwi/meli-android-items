package cl.arech.awesomeitems.products.data.remote

import cl.arech.awesomeitems.products.data.remote.model.RemoteProducts
import cl.arech.awesomeitems.products.data.remote.retrofit.ProductsWebService
import cl.arech.awesomeitems.products.factory.ProductFactory
import cl.arech.testingtools.factory.RandomFactory.generateString
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class ProductsRemoteImplTest {
    private val api = mockk<ProductsWebService>()

    private val remote = ProductsRemoteImpl(api)

    @Test
    fun `given search query, when getProducts, then RemoteProducts`() = runBlocking {
        val query = generateString()
        val products = ProductFactory.makeRemoteProducts(6)

        stubGetProductsAPI(query, products)

        val result = remote.getProducts(query)

        assertEquals(products, result)
    }

    private fun stubGetProductsAPI(
        request: String,
        response: RemoteProducts,
    ) = runBlocking {
        coEvery { api.getProducts(request) } returns response
    }
}