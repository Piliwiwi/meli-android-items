package cl.arech.awesomeitems.products.ui.list.mapper

import cl.arech.awesomeitems.products.presentation.list.model.Product
import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.uicomponents.component.AttrsProductCard
import cl.arech.uicomponents.groupcomponent.productcardlist.AttrsProductCardList
import javax.inject.Inject

class AttrsProductsMapper @Inject constructor() {
    fun Products.toAttrs(onScrollEnd: () -> Unit, onClick: (String) -> Unit) = AttrsProductCardList(
        items = results.toAttrs(),
        onClick = onClick,
        onScrollEnd = onScrollEnd
    )

    private fun List<Product>.toAttrs() = map {
        it.toAttrs()
    }

    private fun Product.toAttrs() = AttrsProductCard(
        identifier = id,
        imageUrl = thumbnail,
        title = title,
        value = price,
        hasFreeShipping = shipping.hasFreeShipping
    )
}