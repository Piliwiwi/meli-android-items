package cl.arech.awesomeitems.products.ui.details

import cl.arech.awesomeitems.products.presentation.list.model.Attribute
import cl.arech.uicomponents.component.detaillist.AttrsDetail
import javax.inject.Inject

class AttrsAttributeMapper @Inject constructor() {
    fun List<Attribute>.toAttrs() = map { it.toAttrs() }.filter { it.value.isNotEmpty() }

    private fun Attribute.toAttrs() = AttrsDetail(
        title = name,
        value = value
    )
}