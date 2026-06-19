package br.edu.ifsp.scl.sc3043894.postviewer.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_comments")
data class LocalComment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "post_id") val postId: Int,
    @ColumnInfo val body: String

)