package com.example.tanahku.ui.classify

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tanahku.ViewModelFactory
import com.example.tanahku.data.local.CropsEntity
import com.example.tanahku.di.Injection
import com.example.tanahku.ui.component.PlantItem
import com.example.tanahku.ui.component.ResultCropsItem
import com.example.tanahku.ui.navigation.Screen
import com.example.tanahku.ui.theme.TanahKuTheme
import com.example.tanahku.utils.UiState


@Composable
fun CropsRecommendation(
    navController: NavHostController,
    viewModel: ClassifyViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val mlResult = navBackStackEntry?.arguments?.getString(Screen.CropsRecommendation.ARG_ML_RESULT) ?: "Tidak Ada"
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xffFBF3E0)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SoilResult(soilName = mlResult)
        Divider(
            color = Color(0xff2e2836),
            modifier = modifier
                .requiredWidth(width = 350.dp))
        Text(
            text = "Tanaman yang cocok pada daerah Anda: ",
            color = Color(0xff2e2836),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .wrapContentHeight(align = Alignment.CenterVertically)
                .padding(top = 2.dp))

        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let{uiState ->
            when(uiState){
                is UiState.Loading -> {
                    viewModel.getCropsBySoil(mlResult)
                    CircularProgressIndicator()
                }
                is UiState.Success -> {
                    val crops = uiState.data
                    Log.d("Recommendation", crops.size.toString())
                    RecommendationList(cropsList = crops, navigateToDetail = navigateToDetail)
                }
                is UiState.Error -> {
                    val errorMessage = uiState.errorMessage
                    Log.d("Recommendation", errorMessage)
                }
            }

        }


    }
}

@Composable
fun SoilResult(modifier: Modifier = Modifier, soilName: String){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 16.dp)
    ) {
        Text(
            text = "Jenis Tanah Anda:  ",
            color = Color(0xff2e2836),
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .wrapContentHeight(align = Alignment.CenterVertically))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(Color(0xffB6D094))
                .padding(horizontal = 32.dp, vertical = 12.dp)
        ) {
            Text(
                text = soilName,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
            )
        }
    }
}

@Composable
fun RecommendationList(
    cropsList: List<CropsEntity>, modifier: Modifier = Modifier, navigateToDetail: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("CropsList")
    ) {
        items(cropsList){ data ->
            Log.d("RecommendationList", "Crop: ${data.name}")
            ResultCropsItem(image = data.image, name = data.name, latinName = data.latinName, desc = data.desc, modifier = Modifier.clickable { navigateToDetail(data.id) })
        }
    }
}