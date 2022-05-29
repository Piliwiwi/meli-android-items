package cl.arech.utils.extension

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.setImage(imageUrl: String) {
    Picasso.get()
        .load(imageUrl)
        .into(this)
}