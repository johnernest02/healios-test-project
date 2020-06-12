package com.healiostestproject.android.screens.details

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
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.Payload
import kotlinx.android.synthetic.main.activity_post_details.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_POST = "post"
    }

    private val viewModel by lifecycleScope.viewModel<DetailsViewModel>(this)

    private lateinit var adapter: FlexibleAdapter<CommentFlexible>

    private var loadingIndicator: IndeterminateTransparentProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_post_details)
        initView()

        var post = intent.getParcelableExtra<Post>(EXTRA_POST)
        if (post != null) {
            viewModel.setPost(post)
        }
    }

    private fun initView() {
        commentsList.layoutManager = LinearLayoutManager(this)
        commentsList.itemAnimator = DefaultItemAnimator()
        commentsList.setHasFixedSize(false)

        adapter = FlexibleAdapter(mutableListOf())
        commentsList.adapter = adapter

        viewModel.loadingState.observe(this, Observer { state ->
            when (state.status) {
                LoadingState.Status.RUNNING -> showLoadingIndicator("Loading post details")
                LoadingState.Status.SUCCESS -> {
                    hideLoadingIndicator()
                    showMessage("Details loaded successfully")
                }
                LoadingState.Status.FAILED -> {
                    hideLoadingIndicator()
                    showMessage(state.msg ?: "Oops, something went wrong", true)
                }
            }
        })

        viewModel.post.observe(this, Observer {
            postTitle.text = it.title
            postBody.text = it.body
        })

        viewModel.user.observe(this, Observer {
            userName.text = "Name: ${it.name}"
            userUsername.text = "Username: ${it.username}"
            userEmailAddress.text = "Email address: ${it.email}"
            userAddress.text =
                "Address: ${it.address.street} ${it.address.suite} ${it.address.city} ${it.address.zipcode} ${it.address.geo.lat} ${it.address.geo.lng}"
            userPhone.text = "Phone: ${it.phone}"
            userWebsite.text = "Website: ${it.website}"
            userCompany.text =
                "Company ${it.company.name} '${it.company.catchPhrase}' ${it.company.bs}"
        })

        viewModel.comments.observe(this, Observer {
            it.forEach { post ->
                val flexible = CommentFlexible(post)
                if (adapter.contains(flexible)) {
                    adapter.updateItem(flexible, Payload.CHANGE)
                } else {
                    adapter.addItem(flexible)
                }
            }
        })
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