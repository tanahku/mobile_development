package com.example.tanahku.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.navigation.NavHostController
import com.example.tanahku.R
import com.example.tanahku.ui.component.Banner
import com.example.tanahku.ui.component.TanahKuIconButton
import com.example.tanahku.ui.navigation.Screen
import com.example.tanahku.ui.theme.TanahKuTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Banner()

        Spacer(modifier = modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.Start),
            modifier = modifier
                .requiredWidth(width = 400.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = Color(0xfffffaef))
                .border(
                    border = BorderStroke(1.dp, Color(0xff6a2e35).copy(alpha = 0.4f)),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_image_24),
                contentDescription = "Photo Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(3.dp, Alignment.Top),
                        modifier = Modifier
                            .weight(weight = 1f)
                    ) {
                        Text(
                            text = "RonggoTM",
                            color = Color(0xff2f1908),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                        Text(
                            text = "musyafart@gmail.com",
                            color = Color(0xff2f1908),
                            style = TextStyle(
                                fontSize = 14.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu_soil),
                            contentDescription = "ph:plant-fill",
                            tint = Color(0xff2f1908),
                            modifier = Modifier
                                .requiredSize(size = 24.dp)
                        )
                        Column(
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Hasil Klasifikasi",
                                color = Color(0xff2f1908),
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 10.sp
                                ),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically)
                            )
                            Text(
                                text = "16 kali",
                                color = Color(0xff2f1908),
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically)
                            )
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu_crops),
                            contentDescription = "ph:plant-fill",
                            tint = Color(0xff2f1908),
                            modifier = Modifier
                                .requiredSize(size = 24.dp)
                        )
                        Column(
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Tanaman Dilihat",
                                color = Color(0xff2f1908),
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically)
                            )
                            Text(
                                text = "11 kali",
                                color = Color(0xff2f1908),
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = modifier.height(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TanahKuIconButton(background = painterResource(id = R.drawable.homebutton),
                title = stringResource(id = R.string.btn_classification),
                desc = stringResource(id = R.string.classification),
                icons = painterResource(id = R.drawable.camera),
                onClick = { })
            TanahKuIconButton(background = painterResource(id = R.drawable.btn_crops),
                title = stringResource(id = R.string.btn_crops),
                desc = stringResource(id = R.string.crops),
                icons = painterResource(id = R.drawable.menu_crops),
                onClick = { })
            TanahKuIconButton(background = painterResource(id = R.drawable.btn_soils),
                title = stringResource(id = R.string.btn_soil),
                desc = stringResource(id = R.string.soil),
                icons = painterResource(id = R.drawable.menu_soil),
                onClick = { })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TanahKuTheme {
        HomeScreen()
    }
}
