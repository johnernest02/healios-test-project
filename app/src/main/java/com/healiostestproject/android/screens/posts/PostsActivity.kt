package com.healiostestproject.android.screens.posts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.healiostestproject.android.IndeterminateTransparentProgressDialog
import com.healiostestproject.android.R
import com.healiostestproject.android.models.LoadingState
import com.healiostestproject.android.models.Post
import com.healiostestproject.android.screens.details.DetailsActivity
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.Payload
import kotlinx.android.synthetic.main.activity_posts.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class PostsActivity : AppCompatActivity() {

    private val viewModel by lifecycleScope.viewModel<PostsViewModel>(this)

    private lateinit var adapter: FlexibleAdapter<PostFlexible>

    private var loadingIndicator: IndeterminateTransparentProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_posts)

        initView()
    }

    private fun initView() {
        postsList.layoutManager = LinearLayoutManager(this)
        postsList.itemAnimator = DefaultItemAnimator()
        adapter = FlexibleAdapter(mutableListOf())
        postsList.adapter = adapter
            .addListener(FlexibleAdapter.OnItemClickListener { view, position ->
                val item = adapter.getItem(position)?.item

                item?.apply {
                    openPostDetails(this)
                }
                false
            })

        viewModel.loadingState.observe(this, Observer { state ->
            when (state.status) {
                LoadingState.Status.RUNNING -> showLoadingIndicator("Loading posts")
                LoadingState.Status.SUCCESS -> {
                    hideLoadingIndicator()
                    showMessage("Posts loaded successfully")
                }
                LoadingState.Status.FAILED -> {
                    hideLoadingIndicator()
                    showMessage(state.msg ?: "Oops, something went wrong", true)
                }
            }
        })

        viewModel.getPosts().observe(this, Observer {
            it.forEach { post ->
                val flexible = PostFlexible(post)
                if (adapter.contains(flexible)) {
                    adapter.updateItem(flexible, Payload.CHANGE)
                } else {
                    adapter.addItem(flexible)
                }
            }
        })
    }

    private fun openPostDetails(post: Post) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.EXTRA_POST, post)
        startActivity(intent)
    }

    private fun showMessage(message: String) {
        val snackbar = Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_LONG)
        val snackBarView = snackbar.view

        // snackBarView.fitsSystemWindows = false
        ViewCompat.setOnApplyWindowInsetsListener(snackBarView, null)
        snackbar.show()
    }

    private fun showMessage(message: String, isError: Boolean) {
        if (isError) {
            val snackbar = Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_LONG)
            val snackBarView = snackbar.view
            // snackBarView.fitsSystemWindows = false
            ViewCompat.setOnApplyWindowInsetsListener(snackBarView, null)
            snackBarView.setBackgroundColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.errorColor,
                    theme
                )
            )
            snackbar.show()
        } else {
            showMessage(message)
        }
    }

    private fun showLoadingIndicator() {
        if (loadingIndicator == null || !loadingIndicator!!.isShowing) {
            runOnUiThread {
                loadingIndicator = IndeterminateTransparentProgressDialog.show(this, true, false)
            }
        }
    }

    private fun showLoadingIndicator(message: String) {
        if (loadingIndicator == null || !loadingIndicator!!.isShowing) {
            runOnUiThread {
                loadingIndicator = IndeterminateTransparentProgressDialog.show(
                    this,
                    true,
                    false,
                    message = message
                )
            }
        }
    }

    private fun hideLoadingIndicator() {
        if (loadingIndicator != null || loadingIndicator!!.isShowing) {
            loadingIndicator!!.dismiss()
        }
    }
}