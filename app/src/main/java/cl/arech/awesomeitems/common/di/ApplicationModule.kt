package cl.arech.awesomeitems.common.di

import android.content.Context
import cl.arech.awesomeitems.common.factory.NetworkDependenciesFactory
import cl.arech.mvi.execution.AppExecutionThread
import cl.arech.mvi.execution.ExecutionThread
import cl.arech.network.config.NetworkDependencies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun providesNetWorkDependencies(
        @ApplicationContext context: Context,
    ): NetworkDependencies =
        NetworkDependenciesFactory.makeNetworkDependencies(context)

    @Provides
    fun providesExecutionThread(): ExecutionThread = AppExecutionThread()
}