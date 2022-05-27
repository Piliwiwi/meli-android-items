package cl.arech.awesomeitems.products.ui.provider

import android.content.Context
import cl.arech.awesomeitems.R
import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.awesomeitems.products.ui.list.mapper.AttrsProductsMapper
import cl.arech.uicomponents.component.AttrsAwesomeSearch
import cl.arech.uicomponents.template.AttrsErrorTemplate
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UiComponentProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val productsMapper: AttrsProductsMapper,
) : UiComponentProvider {
    override val errorTemplateAttrs: AttrsErrorTemplate
        get() = AttrsErrorTemplate(
            title = context.getString(R.string.an_error_has_ocurred),
            description = context.getString(R.string.check_your_internet_conection_and_retry)
        )

    override fun getSearchInputAttrs(onSubmit: (String) -> Unit) = AttrsAwesomeSearch(
        hint = R.string.search_products,
        onSubmit = onSubmit
    )

    override fun getProductListAttrs(
        products: Products,
        onClick: (String) -> Unit,
    ) = with(productsMapper) { products.toAttrs(onClick) }
}