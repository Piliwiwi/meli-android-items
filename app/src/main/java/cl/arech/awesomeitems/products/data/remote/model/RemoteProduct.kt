package cl.arech.awesomeitems.products.data.remote.model

import cl.arech.awesomeitems.products.data.remote.config.Constants.ID
import cl.arech.awesomeitems.products.data.remote.config.Constants.PRICE
import cl.arech.awesomeitems.products.data.remote.config.Constants.SHIPPING
import cl.arech.awesomeitems.products.data.remote.config.Constants.THUMBNAIL
import cl.arech.awesomeitems.products.data.remote.config.Constants.TITLE
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteProduct(
    @Json(name = ID) val id: String?,
    @Json(name = TITLE) val title: String?,
    @Json(name = PRICE) val price: Double?,
    @Json(name = THUMBNAIL) val thumbnail: String?,
    @Json(name = SHIPPING) val shipping: RemoteShipping?,
)