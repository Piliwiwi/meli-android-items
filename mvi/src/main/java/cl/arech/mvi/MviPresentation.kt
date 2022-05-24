package cl.arech.mvi

import cl.arech.mvi.events.MviUiState
import cl.arech.mvi.events.MviUserIntent
import kotlinx.coroutines.flow.Flow

interface MviPresentation<TUserIntent : MviUserIntent, TUiState : MviUiState> {
    fun processUserIntentsAndObserveUiStates(userIntents: Flow<TUserIntent>): Flow<TUiState>
}