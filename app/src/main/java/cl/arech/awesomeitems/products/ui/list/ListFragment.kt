package cl.arech.awesomeitems.products.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import cl.arech.awesomeitems.databinding.FragmentProductsListBinding
import cl.arech.awesomeitems.products.presentation.ListViewModel
import cl.arech.awesomeitems.products.presentation.list.ListUIntent
import cl.arech.awesomeitems.products.presentation.list.ListUIntent.SearchProductsInitialUIntent
import cl.arech.awesomeitems.products.presentation.list.ListUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.DefaultUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.ErrorUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.LoadingUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.ShowProductsUiState
import cl.arech.mvi.MviUi
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

@ExperimentalCoroutinesApi
@FlowPreview
class ListFragment : Fragment(), MviUi<ListUIntent, ListUiState> {
    private var binding: FragmentProductsListBinding? = null

    private val viewModel: ListViewModel by viewModels()

    private val args: ListFragmentArgs by navArgs()

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

    override fun renderUiStates(uiStates: ListUiState) {
        when (uiStates) {
            DefaultUiState -> TODO()
            LoadingUiState -> TODO()
            ErrorUiState -> TODO()
            is ShowProductsUiState -> TODO()
        }
    }

    private fun emit(intent: ListUIntent) {
        viewLifecycleOwner.lifecycleScope.launch {
            userIntents.emit(intent)
        }
    }
}