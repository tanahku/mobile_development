package com.example.tanahku.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tanahku.R
import com.example.tanahku.ui.component.Banner
import com.example.tanahku.ui.component.TanahKuIconButton
import com.example.tanahku.ui.theme.TanahKuTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Banner()
        TanahKuIconButton(background = painterResource(id = R.drawable.homebutton),
            title = stringResource(id = R.string.btn_classification),
            desc = stringResource(id = R.string.classification),
            icons = painterResource(id = R.drawable.camera),
            onClick = { /*TODO*/ })
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TanahKuTheme {
        HomeScreen()
    }
}