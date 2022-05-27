package cl.arech.awesomeitems.products.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import cl.arech.awesomeitems.AwesomeItemsApplication
import cl.arech.awesomeitems.databinding.FragmentProductsListBinding
import cl.arech.awesomeitems.products.presentation.ListViewModel
import cl.arech.awesomeitems.products.presentation.list.ListUIntent
import cl.arech.awesomeitems.products.presentation.list.ListUIntent.SearchAnotherProductsUIntent
import cl.arech.awesomeitems.products.presentation.list.ListUIntent.SearchProductsInitialUIntent
import cl.arech.awesomeitems.products.presentation.list.ListUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.DefaultUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.ErrorUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.LoadingUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.ShowProductsUiState
import cl.arech.awesomeitems.products.presentation.list.mapper.ProductsMapper
import cl.arech.awesomeitems.products.presentation.list.model.Products
import cl.arech.awesomeitems.products.ui.list.mapper.AttrsProductsMapper
import cl.arech.awesomeitems.products.ui.navigator.ProductsNavigator
import cl.arech.awesomeitems.products.ui.provider.UiComponentProvider
import cl.arech.mvi.MviUi
import cl.arech.uicomponents.component.AttrsAwesomeSearch
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@FlowPreview
@ExperimentalCoroutinesApi
class ListFragment : Fragment(), MviUi<ListUIntent, ListUiState> {
    private var binding: FragmentProductsListBinding? = null

    private val viewModel: ListViewModel by viewModels()

    private val args: ListFragmentArgs by navArgs()

    @Inject
    lateinit var navigator: ProductsNavigator

    @Inject
    lateinit var uiProvider: UiComponentProvider

    private val userIntents: MutableSharedFlow<ListUIntent> = MutableSharedFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeStatesProcessIntents()
        setupObservers()
    }

    private fun subscribeStatesProcessIntents() {
        viewModel.run {
            viewModel.processUserIntents(userIntents())
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            uiStates().onEach { renderUiStates(it) }.launchIn(lifecycleScope)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        if (binding == null)
            binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun userIntents(): Flow<ListUIntent> = merge(
        initialUserIntent(),
        userIntents.asSharedFlow()
    )

    private fun initialUserIntent() = flow<ListUIntent> {
        emit(SearchProductsInitialUIntent(args.query))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchBar()
    }

    private fun setSearchBar() = binding?.apply {
        search.isVisible = true

        search.setAttributes(
            uiProvider.getSearchInputAttrs { query ->
                emit(SearchAnotherProductsUIntent(query))
            }
        )
    }

    override fun renderUiStates(uiStates: ListUiState) {
        hideAll()
        when (uiStates) {
            DefaultUiState -> {}
            LoadingUiState -> showLoading()
            ErrorUiState -> showErrorTemplate()
            is ShowProductsUiState -> showProductList(uiStates.products)
        }
    }

    private fun hideAll() = binding?.apply {
        loader.isVisible = false
        productList.isVisible = false
        errorTemplate.isVisible = false
    }

    private fun showLoading() = binding?.apply {
        loader.isVisible = true
    }

    private fun showErrorTemplate() = binding?.apply {
        errorTemplate.isVisible = true

        errorTemplate.setAttributes(uiProvider.errorTemplateAttrs)
    }

    private fun showProductList(products: Products) = binding?.apply {
        productList.isVisible = true

        productList.setAttributes(
            uiProvider.getProductListAttrs(products) { productId ->
                navigator.navigateFromListToDetails(view, productId)
            }
        )
    }

    private fun emit(intent: ListUIntent) {
        viewLifecycleOwner.lifecycleScope.launch {
            userIntents.emit(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}