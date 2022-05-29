package cl.arech.awesomeitems.products.ui.details.mapper

import cl.arech.awesomeitems.products.factory.ProductFactory.makeAttributes
import cl.arech.awesomeitems.products.presentation.list.model.Attribute
import cl.arech.uicomponents.component.detaillist.AttrsDetail
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class AttrsAttributeMapperTest {
    private val mapper = AttrsAttributeMapper()

    @Test
    fun `given Attribute, when toAttrs, then AttrsDetail`() {
        val presentation = makeAttributes(6)

        val attrs = with(mapper) {
            presentation.toAttrs()
        }

        presentation.zip(attrs).map {
            assertAttributeEquals(it.first, it.second)
        }
    }

    private fun assertAttributeEquals(presentation: Attribute, attrs: AttrsDetail) {
        assertEquals(presentation.name, attrs.title, "title")
        assertEquals(presentation.value, attrs.value, "value")
    }
}