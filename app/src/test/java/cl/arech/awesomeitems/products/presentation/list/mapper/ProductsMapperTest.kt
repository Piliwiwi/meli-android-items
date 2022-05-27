package cl.arech.awesomeitems.products.presentation.list.mapper

import cl.arech.awesomeitems.products.data.remote.model.RemoteProduct
import cl.arech.awesomeitems.products.data.remote.model.RemoteShipping
import cl.arech.awesomeitems.products.factory.ProductFactory.makeRemoteProducts
import cl.arech.awesomeitems.products.presentation.list.model.Product
import cl.arech.awesomeitems.products.presentation.list.model.Shipping
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class ProductsMapperTest {
    private val mapper = ProductsMapper()

    @Test
    fun `given RemoteProducts, when toPresentation, then Products`() {
        val remote = makeRemoteProducts(6)

        val presentation = with(mapper) {
            remote.toPresentation()
        }

        remote.results?.zip(presentation.results)?.map {
            assertProductEquals(it.first, it.second)
        }
    }

    private fun assertProductEquals(remote: RemoteProduct?, presentation: Product) {
        assertEquals(remote?.id.orEmpty(), presentation.id, "id")
        assertEquals(remote?.title.orEmpty(), presentation.title, "title")
        assertEquals(remote?.price ?: 0, presentation.price, "price")
        assertEquals(remote?.thumbnail.orEmpty(), presentation.thumbnail, "thumbnail")
        assertShippingEquals(remote?.shipping, presentation.shipping)
    }

    private fun assertShippingEquals(remote: RemoteShipping?, presentation: Shipping) {
        assertEquals(remote?.hasFreeShipping == true,
            presentation.hasFreeShipping,
            "hasFreeShipping")
    }
}