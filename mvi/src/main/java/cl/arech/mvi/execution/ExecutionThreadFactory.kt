package cl.arech.mvi.execution

import cl.arech.mvi.execution.ExecutionThreadEnvironment.APPLICATION
import cl.arech.mvi.execution.ExecutionThreadEnvironment.TESTING

object ExecutionThreadFactory {
    fun makeExecutionThread(environment: ExecutionThreadEnvironment): ExecutionThread =
        when (environment) {
            APPLICATION -> AppExecutionThread()
            TESTING -> AppExecutionThread()
        }
}