package br.edu.ifsp.scl.sc3043894.postviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.edu.ifsp.scl.sc3043894.postviewer.ui.composable.MainNavHost
import br.edu.ifsp.scl.sc3043894.postviewer.ui.composable.MainTopAppBar
import br.edu.ifsp.scl.sc3043894.postviewer.ui.theme.PostViewerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            val postViewModel: PostViewModel = viewModel()
            PostViewerTheme {
                Scaffold(
                    topBar = { MainTopAppBar() },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainNavHost(
                        navController = navController,
                        postViewModel = postViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

