package com.example.openbankchallenge.utils

sealed class Screen(val route: String) {
    object SPLASH : Screen("AnimatedSplashScreen")
    object HOME_SCREEN : Screen("ReciveData")
    object DETAIL_SCREEN : Screen("DetailScreen/{id}")
}
