package cl.arech.awesomeitems.products.data.remote.model

import cl.arech.awesomeitems.products.data.remote.config.Constants.FREE_SHIPPING
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteShipping(
    @Json(name = FREE_SHIPPING) val hasFreeShipping: Boolean?,
)