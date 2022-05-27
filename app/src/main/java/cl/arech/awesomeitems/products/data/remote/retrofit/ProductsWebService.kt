package cl.arech.awesomeitems.products.data.remote.retrofit

import cl.arech.awesomeitems.products.data.remote.config.Constants.Q
import cl.arech.awesomeitems.products.data.remote.model.RemoteProducts
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsWebService {
    @GET("search")
    suspend fun getProducts(@Query(Q) query: String): RemoteProducts
}