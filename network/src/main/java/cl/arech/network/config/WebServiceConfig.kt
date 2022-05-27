package cl.arech.network.config

object WebServiceConfig {
    object Url {
        const val REMOTE_HOST = "https://api.mercadolibre.com/sites/MLA/"
        const val LOCAL_HOST = "http://mock.api/"
    }

    object Timeout {
        const val CONNECT: Long = 60
    }
}