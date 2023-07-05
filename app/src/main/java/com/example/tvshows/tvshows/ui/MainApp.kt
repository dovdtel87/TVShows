package com.example.tvshows.tvshows.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tvshows.ui.theme.TVShowsTheme

@Composable
fun MainApp() {
    val navController = rememberNavController()
    TVShowsTheme {
        NavHost(navController = navController, startDestination = "list") {
            composable("list") {
                //ListShowsScreen()
            }
        }
    }
}