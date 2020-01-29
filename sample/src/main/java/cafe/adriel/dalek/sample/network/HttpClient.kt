package cafe.adriel.dalek.sample.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

val httpClient by lazy {
    HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
}
