package cl.arech.awesomeitems.products.presentation.list.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Attribute(
    val name: String,
    val value: String,
) : Parcelable
