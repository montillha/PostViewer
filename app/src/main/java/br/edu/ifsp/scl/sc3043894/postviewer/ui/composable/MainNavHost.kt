package br.edu.ifsp.scl.sc3043894.postviewer.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.edu.ifsp.scl.sc3043894.postviewer.PostViewModel
import br.edu.ifsp.scl.sc3043894.postviewer.ui.navigation.Screen

@Composable
fun MainNavHost(
    navController: NavHostController,
    postViewModel: PostViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Posts.route
    ) {
        composable(route = Screen.Posts.route) {
            PostsScreen(postViewModel = postViewModel, modifier = modifier) { postId ->
                navController.navigate(Screen.Details.route)
            }
        }

        composable(route = Screen.Details.route) {
            DetailsScreen(postViewModel = postViewModel, modifier = modifier)
        }
    }
}
