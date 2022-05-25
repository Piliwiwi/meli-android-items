package cl.arech.awesomeitems.products.data.remote

import cl.arech.awesomeitems.products.data.remote.model.RemoteProducts
import cl.arech.awesomeitems.products.data.remote.retrofit.ProductsWebService
import cl.arech.awesomeitems.products.data.source.ProductsRemote
import javax.inject.Inject

class ProductsRemoteImpl @Inject constructor(
    private val api: ProductsWebService,
) : ProductsRemote {
    override suspend fun getProducts(query: String): RemoteProducts = api.getProducts(query)
}