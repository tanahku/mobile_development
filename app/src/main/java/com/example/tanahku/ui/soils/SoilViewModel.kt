package com.example.tanahku.ui.soils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanahku.data.TanahkuRepository
import com.example.tanahku.data.local.SoilEntity
import com.example.tanahku.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SoilViewModel(private val repository: TanahkuRepository) : ViewModel() {
    fun insertSoils() {
        viewModelScope.launch {
            repository.insertAllSoils()
        }
    }




    private val _uiState: MutableStateFlow<UiState<List<SoilEntity>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<SoilEntity>>>
        get() = _uiState



    fun getAllSoils() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            repository.getAllSoils { result ->
                _uiState.value = result
            }
        }
    }

}