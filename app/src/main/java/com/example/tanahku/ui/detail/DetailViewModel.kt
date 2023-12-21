package com.example.tanahku.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanahku.data.TanahkuRepository
import com.example.tanahku.data.local.CropsEntity
import com.example.tanahku.data.local.SoilEntity
import com.example.tanahku.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: TanahkuRepository) : ViewModel() {
    private val _uiStateSoil: MutableStateFlow<UiState<SoilEntity>> =
        MutableStateFlow(UiState.Loading)
    val uiStateSoil: StateFlow<UiState<SoilEntity>>
        get() = _uiStateSoil


    private val _uiStateCrops: MutableStateFlow<UiState<CropsEntity>> =
        MutableStateFlow(UiState.Loading)
    val uiStateCrops: StateFlow<UiState<CropsEntity>>
        get() = _uiStateCrops

    fun getSoilById(soilId: Long) {
        viewModelScope.launch {
            _uiStateSoil.value = UiState.Loading
            try {
                // Perform the database operation on a background thread
                val soilEntity = repository.getSoilById(soilId)
                _uiStateSoil.value = UiState.Success(soilEntity)
            } catch (e: Exception) {
                // Handle exceptions, update UI state accordingly
                _uiStateSoil.value = UiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
    fun getCropById(cropId: Int) {
        viewModelScope.launch {
            _uiStateCrops.value = UiState.Loading
            try {
                // Perform the database operation on a background thread
                val cropsEntity = repository.getCropsById(cropId)
                _uiStateCrops.value = UiState.Success(cropsEntity)
            } catch (e: Exception) {
                // Handle exceptions, update UI state accordingly
                _uiStateCrops.value = UiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}