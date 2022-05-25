package cl.arech.awesomeitems.products.data

import cl.arech.awesomeitems.products.data.remote.model.RemoteProducts
import cl.arech.awesomeitems.products.data.source.ProductsRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsDataRepository @Inject constructor(
    private val remote: ProductsRemote,
) {
    fun searchProducts(query: String): Flow<RemoteProducts> = flow {
        val products = remote.getProducts(query)
        emit(products)
    }
}