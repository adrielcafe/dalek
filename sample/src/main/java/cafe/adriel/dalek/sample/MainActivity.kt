package cafe.adriel.dalek.sample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import cafe.adriel.dalek.DalekEvent
import cafe.adriel.dalek.collectIn
import cafe.adriel.dalek.sample.network.Post
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vLoadPost.setOnClickListener { loadRandomPost() }
    }

    private fun loadRandomPost() {
        viewModel.getRandomPost()
            .collectIn(lifecycleScope) { event ->
                when (event) {
                    is DalekEvent.Start -> setLoading(true)
                    is DalekEvent.Success -> showPost(event.value)
                    is DalekEvent.Failure -> showError(event.exception)
                    is DalekEvent.Finish -> setLoading(false)
                }
            }
    }

    private fun showPost(post: Post) {
        listOf(vPostTitle, vPostBody, vLoadPost)
            .forEach { it.isVisible = true }

        post.apply {
            vPostTitle.text = title
            vPostBody.text = body
        }
    }

    private fun showError(exception: Throwable) {
        vLoadPost.isVisible = true

        vErrorState.apply {
            isVisible = true
            text = exception.toString()
        }
    }

    private fun setLoading(loading: Boolean) {
        if (loading) {
            listOf(vPostTitle, vPostBody, vLoadPost, vErrorState)
                .forEach { it.isVisible = false }
            vLoadingState.isVisible = true
        } else {
            vLoadingState.isVisible = false
        }
    }
}
