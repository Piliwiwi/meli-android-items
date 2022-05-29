package cl.arech.awesomeitems.products.presentation.list.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Product(
    val id: String,
    val title: String,
    val price: String,
    val thumbnail: String,
    val stock: String,
    val shipping: Shipping,
    val attributes: List<Attribute>,
) : Parcelable
