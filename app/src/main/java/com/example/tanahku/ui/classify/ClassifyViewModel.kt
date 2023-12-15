package com.example.tanahku.ui.classify

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ClassifyViewModel : ViewModel() {
    private val _currentImageUri = mutableStateOf<Uri?>(null)
    val currentImageUri: State<Uri?> = _currentImageUri

    fun setCurrentImageUri(uri: Uri) {
        _currentImageUri.value = uri
    }
}