package cl.arech.awesomeitems.products.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cl.arech.awesomeitems.databinding.FragmentProductsSearchBinding
import cl.arech.awesomeitems.products.presentation.list.ListUIntent
import cl.arech.awesomeitems.products.ui.navigator.ProductsNavigator
import cl.arech.awesomeitems.products.ui.provider.UiComponentProvider
import cl.arech.uicomponents.component.AttrsAwesomeSearch
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var binding: FragmentProductsSearchBinding? = null

    @Inject
    lateinit var uiProvider: UiComponentProvider

    @Inject
    lateinit var navigator: ProductsNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        if (binding == null)
            binding = FragmentProductsSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearch()
    }

    private fun setupSearch() = binding?.apply {
        searchInput.setAttributes(
            uiProvider.getSearchInputAttrs { query ->
                navigator.navigateFromSearchToList(view, query)
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}