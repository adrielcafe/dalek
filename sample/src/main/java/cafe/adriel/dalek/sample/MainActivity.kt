package cafe.adriel.dalek.sample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import cafe.adriel.dalek.Failure
import cafe.adriel.dalek.Start
import cafe.adriel.dalek.Success
import cafe.adriel.dalek.launchAndCollect
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
            .launchAndCollect(lifecycleScope) { event ->
                when (event) {
                    is Start -> setLoading(true)
                    is Success -> {
                        setLoading(false)
                        showPost(event.value)
                    }
                    is Failure -> {
                        setLoading(false)
                        showError(event.exception)
                    }
                }
            }
    }

    private fun showPost(post: Post) {
        post.apply {
            vPostTitle.text = title
            vPostBody.text = body
        }
    }

    private fun showError(exception: Throwable) {
        val message =
            """
            |ERROR!
            |    
            |$exception
            """.trimMargin()

        vErrorState.apply {
            isVisible = true
            text = message
        }

        exception.printStackTrace()
    }

    private fun setLoading(loading: Boolean) {
        vPostTitle.isVisible = loading.not()
        vPostBody.isVisible = loading.not()
        vLoadPost.isVisible = loading.not()

        vLoadingState.isVisible = loading

        vErrorState.isVisible = false
    }
}
