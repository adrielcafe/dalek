package cafe.adriel.dalek.sample.network

import io.ktor.client.request.get
import kotlin.random.Random

object PostRepository {

    private const val POST_URL = "https://jsonplaceholder.typicode.com/posts/%d"

    private val randomPostId: Long
        get() = Random.nextLong(1, 100)

    private val randomPostUrl: String
        get() = POST_URL.format(randomPostId)

    suspend fun getRandomPost(): Post =
        httpClient.get(randomPostUrl)
}
