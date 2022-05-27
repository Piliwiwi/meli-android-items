package cl.arech.awesomeitems.products.presentation.list.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Shipping(
    val hasFreeShipping: Boolean = false,
) : Parcelable