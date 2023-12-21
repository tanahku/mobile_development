package com.example.tanahku.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tanahku.R
import com.example.tanahku.ui.theme.TanahKuTheme

@Composable
fun ResultCropsItem(
    image: String,
    name: String,
    latinName: String,
    desc: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 350.dp)
            .requiredHeight(height = 89.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color(0xffb6d094))
            .border(
                border = BorderStroke(1.dp, Color(0xff6a2e35)),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(
                horizontal = 12.dp,
                vertical = 13.dp
            )
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
                    contentDescription = "image 3",
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = 0.dp,
                            y = 0.dp
                        )
                        .requiredSize(size = 64.dp)
                        .clip(shape = MaterialTheme.shapes.small)
                        .border(
                            border = BorderStroke(1.dp, Color(0xff4b6d1f)),
                            shape = MaterialTheme.shapes.small
                        )
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .requiredWidth(width = 169.dp)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = name,
                    color = Color(0xff2e2836),
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                )
                Text(
                    text = latinName,
                    color = Color(0xff2e2836),
                    fontStyle = FontStyle.Italic,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium
                    ),
                )
                Text(
                    text = desc,
                    color = Color(0xff2e2836),
                    maxLines = 2,
                    style = TextStyle(
                        fontSize = 8.sp
                    ),
                )
            }

        }
    }
}
