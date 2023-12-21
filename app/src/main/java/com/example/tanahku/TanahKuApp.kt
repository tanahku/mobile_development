package com.example.tanahku

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tanahku.ui.auth.login.LoginScreen
import com.example.tanahku.ui.auth.register.RegisterScreen
import com.example.tanahku.ui.classify.ClassificationResult
import com.example.tanahku.ui.classify.ClassifyScreen
import com.example.tanahku.ui.classify.CropsRecommendation
import com.example.tanahku.ui.crops.CropsScreen
import com.example.tanahku.ui.detail.DetailCropsScreen
import com.example.tanahku.ui.detail.DetailSoilScreen
import com.example.tanahku.ui.home.HomeScreen
import com.example.tanahku.ui.navigation.NavigationItem
import com.example.tanahku.ui.navigation.Screen
import com.example.tanahku.ui.soils.SoilsScreen
import com.example.tanahku.ui.theme.TanahKuTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TanahKuApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Login.route && currentRoute != Screen.Register.route) {
                BottomBar(navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(navController = navController)
            }
            composable(Screen.Register.route) {
                RegisterScreen(navController = navController)
            }
            composable(Screen.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(Screen.Crops.route) {
                CropsScreen(navigateToDetail = {cropId ->
                    navController.navigate(Screen.DetailCrops.createRoute(cropId))
                })
            }
            composable(Screen.Soil.route) {
                SoilsScreen(navigateToDetail = {soilId ->
                    navController.navigate(Screen.DetailSoil.createRoute(soilId))
                })
            }
            composable(Screen.Classify.route) {
                ClassifyScreen(navController = navController, modifier = Modifier.padding(16.dp))
            }
            composable(
                route = "classify/result?imageUri={imageUri}&mlResult={mlResult}",
            ) {
                ClassificationResult(navController = navController)
            }
            composable(
                route = "classify/result/crops-recommendation?mlResult={mlResult}",
            ) {
                CropsRecommendation(navController = navController, navigateToDetail = { cropId ->
                    navController.navigate(Screen.DetailCrops.createRoute(cropId))
                })
            }
            composable(
                route = Screen.DetailSoil.route,
                arguments = listOf(navArgument("soilId"){
                    type = NavType.LongType
                }),
            ){
                val id = it.arguments?.getLong("soilId") ?: -1L
                DetailSoilScreen(soilId = id)
            }
            composable(
                route = Screen.DetailCrops.route,
                arguments = listOf(navArgument("cropsId"){
                    type = NavType.IntType
                }),
            ){
                val id = it.arguments?.getInt("cropsId") ?: -1
                DetailCropsScreen(cropId = id)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TanahKuAppPreview() {
    TanahKuTheme {
        TanahKuApp()
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = painterResource(id = R.drawable.menu_home),
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_crops),
                icon = painterResource(id = R.drawable.menu_crops),
                screen = Screen.Crops
            ),
            NavigationItem(
                title = stringResource(R.string.menu_classify),
                icon = painterResource(id = R.drawable.menu_classify),
                screen = Screen.Classify
            ),
            NavigationItem(
                title = stringResource(R.string.menu_soil),
                icon = painterResource(id = R.drawable.menu_soil),
                screen = Screen.Soil
            ),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            navigationItem.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(item.title) }
                )
            }
        }
    }
}