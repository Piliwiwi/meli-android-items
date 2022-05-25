package cl.arech.awesomeitems.products.factory

import cl.arech.awesomeitems.products.data.remote.model.RemoteProduct
import cl.arech.awesomeitems.products.data.remote.model.RemoteProducts
import cl.arech.awesomeitems.products.data.remote.model.RemoteShipping
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
}