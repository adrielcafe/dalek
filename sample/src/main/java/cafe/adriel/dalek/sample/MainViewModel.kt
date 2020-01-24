package cafe.adriel.dalek.sample

import androidx.lifecycle.ViewModel
import cafe.adriel.dalek.Dalek
import cafe.adriel.dalek.DalekEvent
import cafe.adriel.dalek.sample.network.Post
import cafe.adriel.dalek.sample.network.PostRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

    fun getRandomPost(): Flow<DalekEvent<Post>> =
        Dalek {
            PostRepository.getRandomPost()
        }
}
