package cl.arech.awesomeitems.products.ui.list.mapper

import cl.arech.awesomeitems.products.factory.ProductFactory.makeProducts
import cl.arech.awesomeitems.products.presentation.list.model.Product
import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.uicomponents.component.AttrsProductCard
import cl.arech.uicomponents.groupcomponent.productcardlist.AttrsProductCardList
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class AttrsProductsMapperTest {
    private val mapper = AttrsProductsMapper()

    @Test
    fun `given Products, when toAttrs, then AttrsProductCardList`() {
        val presentation = makeProducts(6)

        val attrs = with(mapper) {
            presentation.toAttrs({}, {})
        }

        assertProductsEquals(presentation, attrs)
    }

    private fun assertProductsEquals(presentation: Products, attrs: AttrsProductCardList) {
        presentation.results.zip(attrs.items).map {
            assertItemsEquals(it.first, it.second)
        }
    }

    private fun assertItemsEquals(presentation: Product, attrs: AttrsProductCard) {
        assertEquals(presentation.id, attrs.identifier, "identifier")
        assertEquals(presentation.thumbnail, attrs.imageUrl, "imageUrl")
        assertEquals(presentation.title, attrs.title, "title")
        assertEquals(presentation.price, attrs.value, "value")
        assertEquals(presentation.shipping.hasFreeShipping,
            attrs.hasFreeShipping,
            "hasFreeShipping")
    }
}