package br.edu.ifsp.scl.sc3043894.postviewer.model.dto

data class Comment(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String,
)
