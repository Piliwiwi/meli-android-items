package cl.arech.mvi

import cl.arech.mvi.events.MviEffect

interface MviUiEffect<in TUiEffect : MviEffect> {
    fun handleEffect(uiEffect: TUiEffect)
}