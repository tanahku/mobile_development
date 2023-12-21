package com.example.tanahku.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tanahku.data.remote.DataPlant

@Composable
fun PlantItem(
    name: String,
    latinName: String,
    image: String,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredWidth(width = 140.dp)
            .requiredHeight(height = 128.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(color = Color(0xfffffaef))
            .border(border = BorderStroke(1.dp, Color(0xff6a2e35)),
                shape = MaterialTheme.shapes.medium)
            .padding(horizontal = 12.dp,
                vertical = 13.dp)
    ) {
        Surface(
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(1.dp, Color(0xff4b6d1f)),
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.small)
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(size = 64.dp)
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = name,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(x = 0.dp,
                            y = 0.dp)
                        .requiredSize(size = 64.dp)
                        .clip(shape = MaterialTheme.shapes.small)
                        .border(border = BorderStroke(1.dp, Color(0xff4b6d1f)),
                            shape = MaterialTheme.shapes.small))
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = name,
                color = Color(0xff2f1908),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium),
                modifier = Modifier
                    .fillMaxWidth())
            Text(
                text = latinName,
                color = Color(0xff2f1908),
                textAlign = TextAlign.Center,
                lineHeight = 1.8.em,
                style = TextStyle(
                    fontSize = 10.sp),
                modifier = Modifier
                    .fillMaxWidth())
        }
    }
}