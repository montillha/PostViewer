package br.edu.ifsp.scl.sc3043894.postviewer.model.api

import br.edu.ifsp.scl.sc3043894.postviewer.model.dto.Comment
import br.edu.ifsp.scl.sc3043894.postviewer.model.dto.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderService {

    @GET("posts")
    suspend fun getPosts() : List<Post>

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") postId: Int): List<Comment>
}