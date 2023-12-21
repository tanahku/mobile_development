package com.example.tanahku.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Crops : Screen("crops")
    object Classify : Screen("classify")
    object Soil : Screen("soil")
    object Login : Screen("login")
    object Register : Screen("register")
    object ClassificationResult : Screen("classify/result") {
        const val ARG_IMAGE_URI = "imageUri"
        const val ARG_ML_RESULT = "mlResult"
    }
    object CropsRecommendation : Screen("classify/result/crops-recommendation") {
        const val ARG_ML_RESULT = "mlResult"
    }
    object DetailSoil : Screen("soil/{soilId}"){
        fun createRoute(soilId: Long) = "soil/$soilId"
    }
    object DetailCrops : Screen("crops/{cropsId}"){
        fun createRoute(cropsId: Int) = "crops/$cropsId"
    }
}