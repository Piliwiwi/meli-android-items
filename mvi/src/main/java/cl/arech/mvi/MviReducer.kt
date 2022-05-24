package cl.arech.mvi

import cl.arech.mvi.events.MviResult
import cl.arech.mvi.events.MviUiState

interface MviReducer<TUiState : MviUiState, TResult : MviResult> {
    infix fun TUiState.reduceWith(result: TResult): TUiState
}