package cl.arech.awesomeitems.products.presentation.list.model

data class Product(
    val id: String,
    val title: String,
    val price: Int,
    val thumbnail: String,
    val shipping: Shipping,
)
