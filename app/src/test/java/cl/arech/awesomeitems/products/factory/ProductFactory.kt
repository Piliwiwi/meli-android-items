package cl.arech.awesomeitems.products.factory

import cl.arech.awesomeitems.products.data.remote.model.RemoteProduct
import cl.arech.awesomeitems.products.data.remote.model.RemoteProducts
import cl.arech.awesomeitems.products.data.remote.model.RemoteShipping
import cl.arech.awesomeitems.products.presentation.list.model.Product
import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.awesomeitems.products.presentation.list.model.Shipping
import cl.arech.testingtools.factory.RandomFactory.generateBoolean
import cl.arech.testingtools.factory.RandomFactory.generateInt
import cl.arech.testingtools.factory.RandomFactory.generateString

object ProductFactory {
    fun makeRemoteProducts(count: Int) = RemoteProducts(
        results = (0..count).map { makeRemoteProduct() }
    )

    private fun makeRemoteProduct() = RemoteProduct(
        title = generateString(),
        price = generateInt(),
        thumbnail = generateString(),
        shipping = makeRemoteShipping()
    )

    private fun makeRemoteShipping() = RemoteShipping(
        hasFreeShipping = generateBoolean()
    )

    fun makeProducts(count: Int) = Products(
        results = (0..count).map { makeProduct() }
    )

    private fun makeProduct() = Product(
        title = generateString(),
        price = generateInt(),
        thumbnail = generateString(),
        shipping = makeShipping()
    )

    private fun makeShipping() = Shipping(
        hasFreeShipping = generateBoolean()
    )
}