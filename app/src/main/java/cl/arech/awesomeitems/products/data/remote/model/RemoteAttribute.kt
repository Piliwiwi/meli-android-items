package cl.arech.awesomeitems.products.data.remote.model

import cl.arech.awesomeitems.products.data.remote.config.Constants.NAME
import cl.arech.awesomeitems.products.data.remote.config.Constants.VALUE_NAME
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteAttribute(
    @Json(name = NAME) val name: String?,
    @Json(name = VALUE_NAME) val value: String?,
)