package cl.arech.awesomeitems.products.ui.provider

import android.content.Context
import cl.arech.awesomeitems.R
import cl.arech.awesomeitems.products.presentation.list.model.Product
import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.awesomeitems.products.ui.list.mapper.AttrsProductsMapper
import cl.arech.uicomponents.component.AttrsAwesomeSearch
import cl.arech.uicomponents.template.AttrsInfoTemplate
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UiComponentProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val productsMapper: AttrsProductsMapper,
) : UiComponentProvider {
    override val errorTemplateAttrs: AttrsInfoTemplate
        get() = AttrsInfoTemplate(
            title = context.getString(R.string.an_error_has_ocurred),
            description = context.getString(R.string.check_your_internet_conection_and_retry)
        )
    override val emptyTemplateAttrs: AttrsInfoTemplate
        get() = AttrsInfoTemplate(
            title = context.getString(R.string.we_have_not_found_awesome_products),
            description = context.getString(R.string.check_that_your_words_are_correctly_written_or_try_another_description)
        )

    override fun getSearchInputAttrs(onSubmit: (String) -> Unit) = AttrsAwesomeSearch(
        hint = R.string.search_products,
        onSubmit = onSubmit
    )

    override fun getProductListAttrs(
        products: Products,
        onClick: (Product) -> Unit,
    ) = with(productsMapper) {
        products.toAttrs { productId ->
            onClick(
                products.results.first { product -> product.id == productId }
            )
        }
    }
}