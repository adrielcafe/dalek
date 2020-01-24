package cafe.adriel.dalek.sample.network

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObject
import com.github.kittinunf.fuel.serialization.kotlinxDeserializerOf
import kotlin.random.Random

object PostRepository {

    private const val POST_URL = "https://jsonplaceholder.typicode.com/posts/%d"

    private val randomPostId: Long
        get() = Random.nextLong(1, 100)

    private val randomPostUrl: String
        get() = POST_URL.format(randomPostId)

    suspend fun getRandomPost(): Post =
        Fuel.get(randomPostUrl)
            .awaitObject(kotlinxDeserializerOf(Post.serializer()))
}
