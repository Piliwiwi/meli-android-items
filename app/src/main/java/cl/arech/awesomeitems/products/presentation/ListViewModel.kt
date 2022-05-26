package cl.arech.awesomeitems.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.arech.awesomeitems.products.presentation.list.ListAction.LoadProductsAction
import cl.arech.awesomeitems.products.presentation.list.ListProcessor
import cl.arech.awesomeitems.products.presentation.list.ListReducer
import cl.arech.awesomeitems.products.presentation.list.ListUIntent
import cl.arech.awesomeitems.products.presentation.list.ListUIntent.RetrySearchUIntent
import cl.arech.awesomeitems.products.presentation.list.ListUIntent.SearchAnotherProductsUIntent
import cl.arech.awesomeitems.products.presentation.list.ListUIntent.SearchProductsInitialUIntent
import cl.arech.awesomeitems.products.presentation.list.ListUiState
import cl.arech.awesomeitems.products.presentation.list.ListUiState.DefaultUiState
import cl.arech.mvi.MviPresentation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class ListViewModel @Inject constructor(
    private val processor: ListProcessor,
    private val reducer: ListReducer,
) : ViewModel(),
    MviPresentation<ListUIntent, ListUiState> {
    private val defaultUiState: ListUiState = DefaultUiState
    private val uiState: MutableStateFlow<ListUiState> = MutableStateFlow(defaultUiState)

    override fun processUserIntents(userIntents: Flow<ListUIntent>) {
        userIntents
            .buffer()
            .flatMapMerge { userIntent ->
                processor.actionProcessor(userIntent.toAction())
            }
            .scan(defaultUiState) { currentUiState, result ->
                with(reducer) { currentUiState reduceWith result }
            }
            .onEach { uiState ->
                this.uiState.value = uiState
            }
            .launchIn(viewModelScope)
    }

    private fun ListUIntent.toAction() =
        when (this) {
            is SearchProductsInitialUIntent -> LoadProductsAction(query)
            is RetrySearchUIntent -> LoadProductsAction(query)
            is SearchAnotherProductsUIntent -> LoadProductsAction(query)
        }

    override fun uiStates() = uiState
}