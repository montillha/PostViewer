package br.edu.ifsp.scl.sc3043894.postviewer.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.sc3043894.postviewer.PostViewModel
import br.edu.ifsp.scl.sc3043894.postviewer.ui.theme.PostViewerTheme
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun PostsScreen(
    postViewModel: PostViewModel,
    modifier: Modifier = Modifier,
    onPostClick: (Int) -> Unit
){

    val posts by postViewModel.posts.collectAsState()
    val isLoading by postViewModel.isLoading.collectAsState()
    val errorMessage by postViewModel.errorMessage.collectAsState()

    Box(
        modifier = modifier.fillMaxSize()
    ){
        when{
            isLoading ->{
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            errorMessage != null -> {
                Text(
                    text = errorMessage ?: "Erro desconhecido",
                    modifier = Modifier.align(Alignment.Center).padding(16.dp)
                )
            }
            else ->{
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(items = posts, key = { it.id }){ post ->
                        Column(
                            modifier = Modifier.fillMaxWidth()
                                .clickable { onPostClick(post.id)}
                                .padding(16.dp)
                        ) {
                            Text(text = post.title, fontSize = 18.sp)
                        }
                        HorizontalDivider()
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostsScreenPreview(){
    PostViewerTheme{
        PostsScreen(postViewModel = viewModel()) {}

    }
}