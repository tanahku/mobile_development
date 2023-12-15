package com.example.tanahku.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tanahku.R
import com.example.tanahku.ui.home.HomeScreen
import com.example.tanahku.ui.theme.TanahKuTheme

@Composable
fun TanahKuIconButton(
    background: Painter,
    title: String,
    desc: String,
    icons: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .height(100.dp) // Adjusted height
            .width(400.dp)  // Adjusted width
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 400.dp)
                .requiredHeight(height = 120.dp)
        ) {
            Image(
                painter = background,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxSize()

            ) {
                Icon(
                    painter = icons,
                    contentDescription = "Camera",
                    tint = Color.White,
                    modifier = modifier
                        .requiredSize(64.dp)
                )
                Column(
                    horizontalAlignment = Alignment.End,

                    ) {
                    Text(
                        text = title,
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )

                    Text(
                        text = desc,
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 14.sp
                        ),
                        modifier = modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun IconButtonPreview() {
    TanahKuTheme {
        TanahKuIconButton(
            background = painterResource(id = R.drawable.btn_crops), 
            title = stringResource(id = R.string.btn_soil),
            desc = stringResource(id = R.string.desc_activity_classify_soil),
            icons = painterResource(id = R.drawable.menu_soil),
            onClick = {},
        )
    }
}

