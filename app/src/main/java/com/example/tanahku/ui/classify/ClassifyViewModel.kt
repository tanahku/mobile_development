package com.example.tanahku.ui.classify

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanahku.data.TanahkuRepository
import com.example.tanahku.data.local.CropsEntity
import com.example.tanahku.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClassifyViewModel(private val repository: TanahkuRepository) : ViewModel() {


    private val _currentImageUri = mutableStateOf<Uri?>(null)
    val currentImageUri: State<Uri?> = _currentImageUri

    private val _uiState: MutableStateFlow<UiState<List<CropsEntity>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<CropsEntity>>>
        get() = _uiState

    fun getCropsBySoil(soil: String) {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            repository.getCropsBySoil(soil) { result ->
                _uiState.value = result
            }
        }
    }
    fun setCurrentImageUri(uri: Uri) {
        _currentImageUri.value = uri
    }
}