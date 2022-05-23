package cl.arech.network.retrofit

import android.content.Context
import cl.arech.network.config.Environment
import cl.arech.network.config.WebServiceConfig
import cl.arech.network.retrofit.exception.InvalidEnvironmentException

class WebServiceFactory<TWebService> constructor(
    private val tClass: Class<TWebService>,
    private val context: Context,
) {

    fun createWebServiceInstance(environment: String): TWebService {
        return when (environment) {
            Environment.Local.name -> createLocalWebServiceConfig()
            Environment.Remote.name -> createRemoteWebServiceConfig(
                baseUrl = WebServiceConfig.Url.REMOTE_HOST
            )
            else -> throw InvalidEnvironmentException("Current environment $environment is not supported")
        }
    }

    private fun createLocalWebServiceConfig(): TWebService =
        LocalWebService<TWebService>().create(
            context = context,
            tClass = tClass,
            hostUrl = WebServiceConfig.Url.LOCAL_HOST
        )

    private fun createRemoteWebServiceConfig(
        baseUrl: String,
    ): TWebService {
        return RemoteWebService<TWebService>().create(
            tClass = tClass,
            baseUrl = baseUrl
        )
    }
}