package br.edu.ifsp.scl.sc3043894.postviewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.scl.sc3043894.postviewer.model.dto.Comment
import br.edu.ifsp.scl.sc3043894.postviewer.model.dto.Post
import br.edu.ifsp.scl.sc3043894.postviewer.model.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {

    val postsRepository = PostRepository
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: StateFlow<List<Comment>> = _comments.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _posts.value = postsRepository.getPosts()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Erro ao carregar posts"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadComments(postId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _comments.value = postsRepository.getComments(postId)
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Erro ao carregar comentários"
            } finally {
                _isLoading.value = false
            }
        }
    }
}