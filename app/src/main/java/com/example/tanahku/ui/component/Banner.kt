package com.example.tanahku.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tanahku.R
import com.example.tanahku.ui.theme.TanahKuTheme

@Composable
fun Banner(modifier: Modifier = Modifier){
    Box(modifier = modifier
        .requiredHeight(200.dp)
        .requiredWidth(336.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.None,
            modifier = Modifier
                .fillMaxSize() // Fill the entire Box
                .align(Alignment.BottomEnd)
        )
        Text(
            text = stringResource(id = R.string.welcome),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .align(Alignment.TopCenter)
            )
    }
}


@Preview(showBackground = true)
@Composable
fun BannerPreview(){
    TanahKuTheme {
        Banner()
    }
}