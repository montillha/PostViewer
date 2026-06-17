package br.edu.ifsp.scl.sc3043894.postviewer.model.repository

import br.edu.ifsp.scl.sc3043894.postviewer.model.api.RetrofitClient
import br.edu.ifsp.scl.sc3043894.postviewer.model.dto.Comment
import br.edu.ifsp.scl.sc3043894.postviewer.model.dto.Post

object PostRepository {

    suspend fun getPosts(): List<Post>{
        return RetrofitClient.service.getPosts()
    }

    suspend fun getComments(postId: Int): List<Comment>{
        return RetrofitClient.service.getComments(postId)
    }
}