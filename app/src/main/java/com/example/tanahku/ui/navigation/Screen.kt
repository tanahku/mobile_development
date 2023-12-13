package com.example.tanahku.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Crops : Screen("crops")
    object Classify : Screen("classify")
    object Soil : Screen("soil")
    object Login : Screen("login")
    object Register : Screen("register")
    object Profile : Screen("profile")
//    object DetailReward : Screen("home/{rewardId}"){
//        fun createRoute(rewardId: Long) = "home/$rewardId"
//    }
}