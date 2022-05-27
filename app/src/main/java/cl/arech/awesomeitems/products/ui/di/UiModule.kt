package cl.arech.awesomeitems.products.ui.di

import cl.arech.awesomeitems.products.ui.provider.UiComponentProvider
import cl.arech.awesomeitems.products.ui.provider.UiComponentProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UiModule {
    @Binds
    abstract fun bindUiComponentProvider(uiProvider: UiComponentProviderImpl): UiComponentProvider
}