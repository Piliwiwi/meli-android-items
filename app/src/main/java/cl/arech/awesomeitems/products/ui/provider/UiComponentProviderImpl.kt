package cl.arech.awesomeitems.products.ui.provider

import cl.arech.awesomeitems.R
import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.awesomeitems.products.ui.list.mapper.AttrsProductsMapper
import cl.arech.uicomponents.component.AttrsAwesomeSearch
import javax.inject.Inject

class UiComponentProviderImpl @Inject constructor(
    private val productsMapper: AttrsProductsMapper,
) : UiComponentProvider {
    override fun getSearchInputAttrs(onSubmit: (String) -> Unit) = AttrsAwesomeSearch(
        hint = R.string.search_products,
        onSubmit = onSubmit
    )

    override fun getProductListAttrs(
        products: Products,
        onClick: (String) -> Unit,
    ) = with(productsMapper) { products.toAttrs(onClick) }
}