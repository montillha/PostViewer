package br.edu.ifsp.scl.sc3043894.postviewer.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
                navController.navigate(Screen.Details.createRoute(postId))
            }
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: return@composable
            DetailsScreen(postViewModel = postViewModel, postId = postId, modifier = modifier)
        }
    }
}
