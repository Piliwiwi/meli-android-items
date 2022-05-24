package cl.arech.mvi

import cl.arech.mvi.events.MviUiState
import cl.arech.mvi.events.MviUserIntent
import kotlinx.coroutines.flow.Flow

interface MviUi<TUserIntent : MviUserIntent, in TUiState : MviUiState> {
    fun userIntents(): Flow<TUserIntent>
    fun renderUiStates(uiStates: TUiState)
}