package cl.arech.awesomeitems.products.ui.provider

import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.uicomponents.component.AttrsAwesomeSearch
import cl.arech.uicomponents.groupcomponent.productcardlist.AttrsProductCardList

interface UiComponentProvider {
    fun getSearchInputAttrs(onSubmit: (String) -> Unit) : AttrsAwesomeSearch
    fun getProductListAttrs(products: Products, onClick: (String) -> Unit) : AttrsProductCardList
}