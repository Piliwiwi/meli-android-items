package cl.arech.awesomeitems.products.ui.provider

import android.content.Context
import cl.arech.awesomeitems.R
import cl.arech.awesomeitems.products.presentation.list.model.Attribute
import cl.arech.awesomeitems.products.presentation.list.model.Product
import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.awesomeitems.products.ui.details.AttrsAttributeMapper
import cl.arech.awesomeitems.products.ui.list.mapper.AttrsProductsMapper
import cl.arech.uicomponents.component.AttrsAwesomeSearch
import cl.arech.uicomponents.component.detaillist.AttrsDetail
import cl.arech.uicomponents.template.AttrsInfoTemplate
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UiComponentProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val productsMapper: AttrsProductsMapper,
    private val attributeMapper: AttrsAttributeMapper,
) : UiComponentProvider {
    override val errorTemplateAttrs: AttrsInfoTemplate
        get() = AttrsInfoTemplate(
            title = context.getString(R.string.an_error_has_ocurred),
            description = context.getString(R.string.check_your_internet_conection_and_retry),
            loop = false
        )
    override val emptyTemplateAttrs: AttrsInfoTemplate
        get() = AttrsInfoTemplate(
            title = context.getString(R.string.we_have_not_found_awesome_products),
            description = context.getString(R.string.check_that_your_words_are_correctly_written_or_try_another_description),
            loop = true
        )

    override fun getSearchInputAttrs(onSubmit: (String) -> Unit) = AttrsAwesomeSearch(
        hint = R.string.search_products,
        onSubmit = onSubmit
    )

    override fun getDetailsAttrs(attributes: List<Attribute>): List<AttrsDetail> =
        with(attributeMapper) { attributes.toAttrs() }

    override fun getProductListAttrs(
        products: Products,
        onClick: (Product) -> Unit,
        onScrollEnd: () -> Unit,
    ) = with(productsMapper) {
        products.toAttrs(onScrollEnd) { productId ->
            onClick(
                products.results.first { product -> product.id == productId }
            )
        }
    }
}