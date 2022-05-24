package cl.arech.awesomeitems.common.di

import android.content.Context
import cl.arech.awesomeitems.common.factory.NetworkDependenciesFactory
import cl.arech.network.config.NetworkDependencies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun providesNetWorkDependencies(
        context: Context,
    ): NetworkDependencies =
        NetworkDependenciesFactory.makeNetworkDependencies(context)
}