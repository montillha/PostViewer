package br.edu.ifsp.scl.sc3043894.postviewer.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifsp.scl.sc3043894.postviewer.PostViewModel
import br.edu.ifsp.scl.sc3043894.postviewer.ui.theme.PostViewerTheme



@Composable
fun DetailsScreen(
    postViewModel: PostViewModel,
    postId: Int,
    modifier: Modifier = Modifier

) {

    LaunchedEffect(postId){
        postViewModel.loadComments(postId)
    }

    val posts by postViewModel.posts.collectAsState()
    val comments by postViewModel.comments.collectAsState()
    val isLoading by postViewModel.isLoading.collectAsState()
    val errorMessage by postViewModel.errorMessage.collectAsState()


    val post = posts.find { it.id == postId }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if(post != null){
            Text(
                text = post.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().padding(24.dp)

            )

        }


        Box( modifier = Modifier.fillMaxSize()){
            when{
                isLoading ->{
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                errorMessage != null ->{
                    Text(
                        text = errorMessage ?: "Erro desconhecido"
                    )
                }
                else ->{
                    LazyColumn( modifier = Modifier.fillMaxSize()) {
                        items(items = comments, key = {it.id}){ comment ->
                            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {

                                Text(text = comment.name, fontWeight = FontWeight.Bold)
                                Text(text = comment.email, style = MaterialTheme.typography.bodySmall)
                                Text(text = comment.body, Modifier.padding(10.dp))
                            }
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview(){
    PostViewerTheme() {
        DetailsScreen(postViewModel = viewModel(), postId = 1)
    }
}