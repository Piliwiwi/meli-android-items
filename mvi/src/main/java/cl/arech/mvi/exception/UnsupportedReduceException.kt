package cl.arech.mvi.exception

import cl.arech.mvi.events.MviResult
import cl.arech.mvi.events.MviUiState
import java.lang.RuntimeException

class UnsupportedReduceException(state: MviUiState, result: MviResult) :
    RuntimeException("Cannot reduce state: $state with result: $result")