package cl.arech.awesomeitems.products.ui.provider

import cl.arech.awesomeitems.products.presentation.list.model.Product
import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.uicomponents.component.AttrsAwesomeSearch
import cl.arech.uicomponents.groupcomponent.productcardlist.AttrsProductCardList
import cl.arech.uicomponents.template.AttrsInfoTemplate

interface UiComponentProvider {
    val errorTemplateAttrs: AttrsInfoTemplate
    val emptyTemplateAttrs: AttrsInfoTemplate

    fun getSearchInputAttrs(onSubmit: (String) -> Unit): AttrsAwesomeSearch
    fun getProductListAttrs(
        products: Products,
        onClick: (Product) -> Unit,
        onScrollEnd: () -> Unit,
    ): AttrsProductCardList
}