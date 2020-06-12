package com.healiostestproject.android.screens.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.healiostestproject.android.backend.DataProvider
import com.healiostestproject.android.models.LoadingState
import com.healiostestproject.android.models.Post
import kotlinx.coroutines.launch
import java.io.IOException

class PostsViewModel(
    private val dataProvider: DataProvider
) : ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()

    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val posts: MutableLiveData<List<Post>> by lazy {
        MutableLiveData<List<Post>>().also {
            loadPosts()
        }
    }

    fun getPosts(): LiveData<List<Post>> {
        return posts
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _loadingState.postValue(LoadingState.LOADING)
            try {
                val items = dataProvider.loadPosts()
                posts.postValue(items)
                _loadingState.postValue(LoadingState.LOADED)
            } catch (exception: IOException) {
                _loadingState.postValue(LoadingState.error(exception.message))
            }
        }
    }

    override fun onCleared() {

    }
}