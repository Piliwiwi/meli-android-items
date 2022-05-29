package cl.arech.awesomeitems.products.presentation.list.mapper

import cl.arech.awesomeitems.products.data.remote.model.RemoteAttribute
import cl.arech.awesomeitems.products.data.remote.model.RemoteProduct
import cl.arech.awesomeitems.products.data.remote.model.RemoteProducts
import cl.arech.awesomeitems.products.data.remote.model.RemoteShipping
import cl.arech.awesomeitems.products.presentation.list.model.Attribute
import cl.arech.awesomeitems.products.presentation.list.model.Product
import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.awesomeitems.products.presentation.list.model.Shipping
import cl.arech.utils.formatter.toAmountFormat
import javax.inject.Inject

class ProductsMapper @Inject constructor() {
    fun RemoteProducts.toPresentation() = Products(
        results = results?.map { remoteProduct -> remoteProduct.toPresentation() }.orEmpty()
    )

    private fun RemoteProduct.toPresentation() = Product(
        id = id.orEmpty(),
        title = title.orEmpty(),
        price = price?.toAmountFormat().orEmpty(),
        thumbnail = thumbnail.orEmpty(),
        stock = stock?.toString().orEmpty(),
        shipping = shipping?.toPresentation() ?: Shipping(),
        attributes = attributes?.map { it.toPresentation() }.orEmpty()
    )

    private fun RemoteShipping.toPresentation() = Shipping(
        hasFreeShipping = hasFreeShipping == true
    )

    private fun RemoteAttribute.toPresentation() = Attribute(
        name = name.orEmpty(),
        value = value.orEmpty()
    )
}