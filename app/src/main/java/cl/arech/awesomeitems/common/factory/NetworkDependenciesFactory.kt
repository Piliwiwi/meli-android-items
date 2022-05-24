package cl.arech.awesomeitems.common.factory

import android.content.Context
import cl.arech.network.config.NetworkDependencies

object NetworkDependenciesFactory {
    fun makeNetworkDependencies(context: Context): NetworkDependencies =
        NetworkDependencies(
            flavorName = "",//BuildConfig.FLAVOR,
            context = context
        )
}