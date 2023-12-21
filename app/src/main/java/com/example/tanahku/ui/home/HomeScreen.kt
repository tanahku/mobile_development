package com.example.tanahku.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tanahku.R
import com.example.tanahku.ui.component.Banner
import com.example.tanahku.ui.component.TanahKuIconButton
import com.example.tanahku.ui.navigation.Screen
import com.example.tanahku.ui.theme.TanahKuTheme

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Banner(title = stringResource(R.string.welcome))
        Spacer(modifier = modifier.height(16.dp))
        TanahKuIconButton(background = painterResource(id = R.drawable.homebutton),
            title = stringResource(id = R.string.btn_classification),
            desc = stringResource(id = R.string.classification),
            icons = painterResource(id = R.drawable.camera),
            modifier = Modifier.clickable {
                navController.navigate(Screen.Classify.route) {
                    // Clear the back stack up to the Home route
                    popUpTo(Screen.Home.route) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            })
        TanahKuIconButton(background = painterResource(id = R.drawable.btn_crops),
            title = stringResource(id = R.string.btn_crops),
            desc = stringResource(id = R.string.crops),
            icons = painterResource(id = R.drawable.menu_crops),
            modifier = Modifier.clickable {
                navController.navigate(Screen.Crops.route) {
                    // Clear the back stack up to the Home route
                    popUpTo(Screen.Home.route) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            })
        TanahKuIconButton(background = painterResource(id = R.drawable.btn_soils),
            title = stringResource(id = R.string.btn_soil),
            desc = stringResource(id = R.string.soil),
            icons = painterResource(id = R.drawable.menu_soil),
            modifier = Modifier.clickable {
                navController.navigate(Screen.Soil.route) {
                    // Clear the back stack up to the Home route
                    popUpTo(Screen.Home.route) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            })
    }
}


