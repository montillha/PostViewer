package br.edu.ifsp.scl.sc3043894.postviewer.model.repository

import android.content.Context
import androidx.room.Room
import br.edu.ifsp.scl.sc3043894.postviewer.model.database.AppDatabase
import br.edu.ifsp.scl.sc3043894.postviewer.model.entity.LocalComment

object LocalCommentRepository {
    private lateinit var applicationContext: Context
    fun init(applicationContext: Context) {
        this.applicationContext = applicationContext
    }

    private val localCommentDaoImpl by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "post_viewer_database"
        ).build().getLocalCommentDao()
    }

    fun getCommentsByPostId(postId: Int) = localCommentDaoImpl.getCommentsByPostId(postId)
    fun addComment(comment: LocalComment) = localCommentDaoImpl.addComment(comment)
}
