package cl.arech.awesomeitems.products.factory

import cl.arech.awesomeitems.products.data.remote.model.RemoteAttribute
import cl.arech.awesomeitems.products.data.remote.model.RemoteProduct
import cl.arech.awesomeitems.products.data.remote.model.RemoteProducts
import cl.arech.awesomeitems.products.data.remote.model.RemoteShipping
import cl.arech.awesomeitems.products.presentation.list.model.Attribute
import cl.arech.awesomeitems.products.presentation.list.model.Product
import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.awesomeitems.products.presentation.list.model.Shipping
import cl.arech.testingtools.factory.RandomFactory.generateBoolean
import cl.arech.testingtools.factory.RandomFactory.generateDouble
import cl.arech.testingtools.factory.RandomFactory.generateInt
import cl.arech.testingtools.factory.RandomFactory.generateString

object ProductFactory {
    fun makeRemoteProducts(count: Int) = RemoteProducts(
        results = (0..count).map { makeRemoteProduct(count) }
    )

    private fun makeRemoteProduct(count: Int) = RemoteProduct(
        id = generateString(),
        title = generateString(),
        price = generateDouble(),
        thumbnail = generateString(),
        stock = generateInt(),
        shipping = makeRemoteShipping(),
        attributes = makeRemoteAttributes(count)
    )

    private fun makeRemoteShipping() = RemoteShipping(
        hasFreeShipping = generateBoolean()
    )

    private fun makeRemoteAttributes(count: Int) =
        (0..count).map { makeRemoteAttribute() }

    private fun makeRemoteAttribute() = RemoteAttribute(
        name = generateString(),
        value = generateString()
    )

    fun makeProducts(count: Int) = Products(
        results = (0..count).map { makeProduct(count) }
    )

    fun makeEmptyProducts() = Products(
        results = emptyList()
    )

    private fun makeProduct(count: Int) = Product(
        id = generateString(),
        title = generateString(),
        price = generateString(),
        thumbnail = generateString(),
        stock = generateString(),
        shipping = makeShipping(),
        attributes = makeAttributes(count)
    )

    private fun makeShipping() = Shipping(
        hasFreeShipping = generateBoolean()
    )

    fun makeAttributes(count: Int) =
        (0..count).map { makeAttribute() }

    private fun makeAttribute() = Attribute(
        name = generateString(),
        value = generateString()
    )
}