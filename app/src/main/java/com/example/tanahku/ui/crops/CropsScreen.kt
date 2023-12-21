package com.example.tanahku.ui.crops

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tanahku.R
import com.example.tanahku.ViewModelFactory
import com.example.tanahku.data.local.CropsEntity
import com.example.tanahku.data.remote.DataPlant
import com.example.tanahku.di.Injection
import com.example.tanahku.ui.component.Banner
import com.example.tanahku.ui.component.PlantItem
import com.example.tanahku.ui.theme.TanahKuTheme
import com.example.tanahku.utils.UiState

@Composable
fun CropsScreen(
    modifier: Modifier = Modifier,
    viewModel: CropsViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateToDetail: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Banner(
            title = stringResource(R.string.crops_title),
            desc = stringResource(id = R.string.crops_desc)
        )

        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllCrops()
                }

                is UiState.Success -> {
                    val data = uiState.data
                    CropsList(
                        cropsList = data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail
                    )
                    viewModel.insertCrops(data)
                }

                is UiState.Error -> {
                }
            }
        }

    }
}

@Composable
fun CropsList(
    cropsList: List<CropsEntity>, modifier: Modifier = Modifier, navigateToDetail: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("CropsList")
    ) {
        items(cropsList) { data ->
            PlantItem(name = data.name, latinName = data.latinName, image = data.image, modifier = Modifier.clickable { navigateToDetail(data.id) })
        }
    }
}
