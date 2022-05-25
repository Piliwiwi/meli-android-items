package cl.arech.awesomeitems.products.data.source

import cl.arech.awesomeitems.products.data.remote.model.RemoteProducts

interface ProductsRemote {
    suspend fun getProducts(query: String): RemoteProducts
}