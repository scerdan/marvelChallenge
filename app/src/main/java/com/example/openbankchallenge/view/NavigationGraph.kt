package com.example.openbankchallenge.view

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.openbankchallenge.utils.Screen
import com.example.openbankchallenge.viewmodel.AppViewModel

@ExperimentalMaterialApi
@Composable
fun NavigationGraph(viewModel: AppViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SPLASH.route) {

        composable(route = Screen.SPLASH.route) {
            AnimatedSplashScreen(navController)
        }

        composable(route = Screen.HOME_SCREEN.route) {
            ReciveData(viewModel, navController)
        }
        composable(
            route = Screen.DETAIL_SCREEN.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
            )
        )
        {
            DetailScreen(it, viewModel)
        }
    }
}

