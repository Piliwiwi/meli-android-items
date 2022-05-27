package cl.arech.awesomeitems.products.ui.di

import cl.arech.awesomeitems.products.data.remote.ProductsRemoteImpl
import cl.arech.awesomeitems.products.data.remote.retrofit.ProductsWebService
import cl.arech.awesomeitems.products.data.source.ProductsRemote
import cl.arech.network.config.NetworkDependencies
import cl.arech.network.retrofit.WebServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    abstract fun bindRemote(remote: ProductsRemoteImpl): ProductsRemote

    companion object {
        @Provides
        fun providesWebServiceRetrofit(
            dependencies: NetworkDependencies,
        ): ProductsWebService = WebServiceFactory(
            tClass = ProductsWebService::class.java,
            context = dependencies.context,
        ).createWebServiceInstance(dependencies.flavorName)
    }
}