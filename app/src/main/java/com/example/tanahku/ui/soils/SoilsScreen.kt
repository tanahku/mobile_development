package com.example.tanahku.ui.soils

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.tanahku.R
import com.example.tanahku.ViewModelFactory
import com.example.tanahku.data.local.SoilEntity
import com.example.tanahku.di.Injection
import com.example.tanahku.ui.component.Banner
import com.example.tanahku.utils.UiState

@Composable
fun SoilsScreen(
    viewModel: SoilViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.getAllSoils()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Banner(title = "Jenis Tanah", desc = "Lihat semua ragam tanaman yang tersedia di sini")
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                }

                is UiState.Success -> {
                    val data = uiState.data
                    Log.d("Soils", data.size.toString())
                    SoilList(soils = data, navigateToDetail = navigateToDetail)
                }

                is UiState.Error -> {
                    val errorMessage = uiState.errorMessage
                    Log.d("Soils", errorMessage)
                }
            }
        }
    }
}

@Composable
fun SoilList(
    soils: List<SoilEntity>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("CropsList")
    ) {
        items(soils) { data ->

            SoilsItem(
                name = data.type,
                englishName = data.englishName,
                image = painterResource(data.image),
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}

@Composable
fun SoilsItem(
    name: String,
    englishName: String,
    image: Painter,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .requiredWidth(width = 336.dp)
            .requiredHeight(height = 80.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .border(
                border = BorderStroke(1.dp, Color(0xff6a2e35)),
                shape = MaterialTheme.shapes.medium
            )

    ) {
        Image(
            painter = painterResource(id = R.drawable.soil_background),
            contentDescription = "Intersect",
            modifier = Modifier
        )
        Column(
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .offset(
                    x = 88.dp,
                    y = 0.5.dp
                )
        ) {
            Text(
                text = name,
                color = Color(0xff2f1908),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
            Text(
                text = englishName,
                color = Color(0xff2f1908),
                style = TextStyle(
                    fontSize = 10.sp
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
        Surface(
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(1.dp, Color(0xff6a2e35)),
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .offset(
                    x = 10.dp,
                    y = 0.dp
                )
                .clip(shape = MaterialTheme.shapes.small)
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(size = 60.dp)
            ) {
                Image(
                    painter = image,
                    contentDescription = "Chalky-Soil 1",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .requiredWidth(width = 60.dp)
                        .requiredHeight(height = 68.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                )
            }
        }
    }
}

