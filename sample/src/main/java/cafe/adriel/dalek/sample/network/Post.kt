package cafe.adriel.dalek.sample.network

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
)
