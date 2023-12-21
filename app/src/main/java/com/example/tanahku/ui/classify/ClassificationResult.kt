package com.example.tanahku.ui.classify

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberImagePainter
import com.example.tanahku.R
import com.example.tanahku.ViewModelFactory
import com.example.tanahku.di.Injection
import com.example.tanahku.ui.navigation.Screen

@Composable
fun ClassificationResult(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val imageUri = navBackStackEntry?.arguments?.getString(Screen.ClassificationResult.ARG_IMAGE_URI)?.let { Uri.parse(it) }
    val mlResult = navBackStackEntry?.arguments?.getString(Screen.ClassificationResult.ARG_ML_RESULT) ?: "Tidak Ada"
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = Color(0xffefd3ae))
            .padding(all = 16.dp)
    ) {
        var soilId by remember{ mutableStateOf(0L) }

        when(mlResult){
            "Chalk" -> {
                soilId = 1
            }
            "Clay" -> {
                soilId = 2
            }
            "Loam" -> {
                soilId = 3
            }
            "Peat" -> {
                soilId = 4
            }
            "Sand" -> {
                soilId = 5
            }
            "Silt" -> {
                soilId = 6
            }
        }

        Box(
            modifier = Modifier
                .requiredSize(380.dp, 430.dp)
                .wrapContentSize(Alignment.Center)
                .padding(16.dp)
        ) {
            Image(
                painter = if (imageUri?.path?.isNotEmpty() == true) {
                    rememberImagePainter(imageUri)
                } else {
                    painterResource(id = R.drawable.baseline_image_24)
                },
                contentDescription = "Foto Tanah",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        border = BorderStroke(2.dp, Color(0xff1f1104)),
                        shape = RoundedCornerShape(10.dp)
                    )
            )
        }
        Text(
            text = "Foto anda terdeteksi sebagai:",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color(0xff2F1908)
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(Color(0xff2F1908))
                .padding(horizontal = 32.dp, vertical = 12.dp)
        ) {
            Text(
                text = if (mlResult.isEmpty()) {
                    "Tidak ada"
                } else {
                    "$mlResult Soil"
                },
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.White
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ResultButton(
                title = "Lihat tanaman yang cocok dengan tanah anda",
                icon = painterResource(id = R.drawable.menu_crops),
                onClick = {
                    navController.navigate(
                    "${Screen.CropsRecommendation.route}?${Screen.CropsRecommendation.ARG_ML_RESULT}=${mlResult}"
                )}
            )
            ResultButton(
                title = "Baca selengkapnya tentang $mlResult",
                icon = painterResource(id = R.drawable.menu_soil),
                onClick = {
                    navController.navigate(Screen.DetailSoil.createRoute(soilId))
                }
            )
        }
    }
}




@Composable
fun ResultButton(
    title: String,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(100.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xfffbf3e0)),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
        border = BorderStroke(1.dp, Color(0xff2f1908)),
        modifier = modifier
            .width(320.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Icon(
                painter = icon,
                contentDescription = "icon",
                tint = Color(0xff2f1908),
                modifier = Modifier
                    .requiredSize(size = 28.dp)
            )
            Text(
                text = title,
                color = Color(0xff2f1908),
                style = TextStyle(
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .requiredWidth(width = 156.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "maki:arrow",
                tint = Color(0xff2f1908),
                modifier = Modifier
                    .requiredSize(size = 28.dp)
            )
        }
    }
}