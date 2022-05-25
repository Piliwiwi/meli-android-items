package cl.arech.awesomeitems.products.data.remote.model

import cl.arech.awesomeitems.products.data.remote.config.Constants.RESULTS
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteProducts(
    @Json(name = RESULTS) val results: List<RemoteProduct>?,
)