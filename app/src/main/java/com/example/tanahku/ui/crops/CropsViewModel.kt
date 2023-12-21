package com.example.tanahku.ui.crops

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanahku.data.TanahkuRepository
import com.example.tanahku.data.local.CropsEntity
import com.example.tanahku.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CropsViewModel(private val repository: TanahkuRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<CropsEntity>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<CropsEntity>>>
        get() = _uiState
    fun getAllCrops() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            // Check if there is data in the Room database
            val cropsFromDatabase = repository.getCropsFromDatabase()

            if (cropsFromDatabase.isNotEmpty()) {
                // Use data from the database
                _uiState.value = UiState.Success(cropsFromDatabase)
            } else {
                // Fetch data from the API
                repository.getAllCrops { result ->
                    _uiState.value = result
                    if (result is UiState.Success) {
                        // Insert data into Room database
                        insertCrops(result.data)
                    }
                }
            }
        }
    }

    fun insertCrops(crops: List<CropsEntity>) {
        viewModelScope.launch {
            repository.saveCropsToDatabase(crops)
        }
    }

}