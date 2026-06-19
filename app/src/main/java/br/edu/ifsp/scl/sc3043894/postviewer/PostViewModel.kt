package br.edu.ifsp.scl.sc3043894.postviewer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.scl.sc3043894.postviewer.model.api.PostsService
import br.edu.ifsp.scl.sc3043894.postviewer.model.dto.Comment
import br.edu.ifsp.scl.sc3043894.postviewer.model.dto.Post
import br.edu.ifsp.scl.sc3043894.postviewer.model.entity.LocalComment
import br.edu.ifsp.scl.sc3043894.postviewer.model.repository.LocalCommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PostViewModel(application: Application): AndroidViewModel(application) {

    val postsService = PostsService
    val localCommentRepository = LocalCommentRepository
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: StateFlow<List<Comment>> = _comments.asStateFlow()


    private val _localComments = MutableStateFlow<List<LocalComment>>(emptyList())
    val localComments: StateFlow<List<LocalComment>> = _localComments.asStateFlow()

    private val _currentPostId = MutableStateFlow<Int?>(null)
    val currentPostId: StateFlow<Int?> = _currentPostId.asStateFlow()

    private val _allComments = MutableStateFlow<List<Comment>>(emptyList())
    val allComments: StateFlow<List<Comment>> = _comments.asStateFlow()

    fun setCurrentPostId(postId: Int) {
        _currentPostId.value = postId
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        localCommentRepository.init(application)
        loadPosts()
        loadAllComments()

    }


    fun getTotalComment(postId: Int) : Int{

        return _allComments.value.filter {it -> it.postId == postId }.count()

    }

    fun loadAllComments(){
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _allComments.value = postsService.getAllComments()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Erro ao carregar comentários"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _posts.value = postsService.getPosts()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Erro ao carregar posts"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun  loadComments(postId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _comments.value = postsService.getComments(postId)
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Erro ao carregar comentários"
            } finally {
                _isLoading.value = false
            }
        }
        loadLocalComments(postId)
    }

    private fun loadLocalComments(postId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _localComments.value = localCommentRepository.getCommentsByPostId(postId)
        }
    }

    fun addLocalComment(postId: Int, body: String){
        viewModelScope.launch(Dispatchers.IO) {
            localCommentRepository.addComment(LocalComment(postId = postId, body = body))
            loadLocalComments(postId)
        }
    }
}