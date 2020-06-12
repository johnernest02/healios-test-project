package com.healiostestproject.android.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.healiostestproject.android.backend.DataProvider
import com.healiostestproject.android.models.Comment
import com.healiostestproject.android.models.LoadingState
import com.healiostestproject.android.models.Post
import com.healiostestproject.android.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class DetailsViewModel(
    private val dataProvider: DataProvider
) : ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()

    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private var _post = MutableLiveData<Post>()

    val post: LiveData<Post>
        get() = _post

    private var _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user

    private var _comments = MutableLiveData<List<Comment>>()

    val comments: LiveData<List<Comment>>
        get() = _comments

    fun setPost(post: Post) {
        _post.postValue(post)

        viewModelScope.launch(Dispatchers.IO) {
            _loadingState.postValue(LoadingState.LOADING)
            try {
                _user.postValue(dataProvider.getUser(post.userId))
                _comments.postValue(dataProvider.getComments(postID = post.id))

                _loadingState.postValue(LoadingState.LOADED)
            } catch (exception: IOException) {
                _loadingState.postValue(LoadingState.error(exception.message))
            }
        }
    }
}