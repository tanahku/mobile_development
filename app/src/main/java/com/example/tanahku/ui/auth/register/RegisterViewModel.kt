package com.example.tanahku.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanahku.data.TanahkuRepository
import com.example.tanahku.data.remote.RegisterResponse
import com.example.tanahku.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: TanahkuRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<RegisterResponse>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<RegisterResponse>>
        get() = _uiState
    private val _uiStateLiveData: MutableLiveData<UiState<RegisterResponse>> = MutableLiveData()
    val uiStateLiveData: LiveData<UiState<RegisterResponse>> = _uiStateLiveData

    val registerResponse: LiveData<RegisterResponse> = repository.registerResponse

    fun postUser(username: String, email: String, pass: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            // Attempt to login using credentials
            repository.registerUser(username, email, pass) { result ->
                _uiState.value = result
                _uiStateLiveData.value = result
            }
        }
    }
}
