package com.example.tanahku.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tanahku.data.TanahkuRepository
import com.example.tanahku.data.pref.UserModel
import com.example.tanahku.data.remote.LoginResponse
import com.example.tanahku.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel( private val repository: TanahkuRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<LoginResponse>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<LoginResponse>>
        get() = _uiState

    private val _uiStateLiveData: MutableLiveData<UiState<LoginResponse>> = MutableLiveData()
    val uiStateLiveData: LiveData<UiState<LoginResponse>> = _uiStateLiveData

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun loginUser(email: String, pass: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            // Attempt to login using credentials
            repository.loginUser(email, pass) { result ->
                _uiState.value = result
                _uiStateLiveData.value = result
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            repository.login()
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

}