package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MovieNavigation() {
    val navControl = rememberNavController()
    NavHost(
        navController = navControl,
        startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name) {
//            HomeScreen()
        }
    }
}