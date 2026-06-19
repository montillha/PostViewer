package br.edu.ifsp.scl.sc3043894.postviewer.ui.navigation

sealed class Screen(val route: String) {
    object Posts : Screen(route = "posts")
    object Details : Screen(route = "details")
}