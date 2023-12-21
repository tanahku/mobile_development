package com.example.tanahku.ui.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tanahku.R
import com.example.tanahku.ViewModelFactory
import com.example.tanahku.di.Injection
import com.example.tanahku.ui.theme.TanahKuTheme
import com.example.tanahku.utils.UiState


@Composable
fun DetailSoilScreen(
    soilId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    modifier: Modifier = Modifier){
    viewModel.uiStateSoil.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getSoilById(soilId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailSoil(
                    data.image,
                    data.type,
                    data.englishName,
                    data.desc
                )
            }
            is UiState.Error -> {}
        }
    }

}

@Composable
fun DetailSoil(
    image: Int,
    name: String,
    englishName: String,
    desc: String,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffFBF3E0))
            .verticalScroll(rememberScrollState())
            .padding(top = 32.dp, bottom = 12.dp)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = name,
            modifier = modifier
                .requiredSize(size = 312.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .border(
                    border = BorderStroke(1.dp, Color(0xff1f1104)),
                    shape = RoundedCornerShape(5.dp)
                )
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .requiredWidth(width = 304.dp)
                .padding(vertical = 24.dp)
        ) {
            Text(
                text = name,
                color = Color(0xff2e2836),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.CenterVertically))
            Text(
                text = englishName,
                color = Color(0xff2e2836),
                fontStyle = FontStyle.Italic,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.CenterVertically))

            Text(
                text = desc,
                color = Color(0xff2e2836),
                style = TextStyle(
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify),
                modifier = modifier
                    .requiredWidth(width = 304.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically))
        }

    }
}
