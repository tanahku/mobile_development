package com.example.tanahku

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tanahku.data.TanahkuRepository
import com.example.tanahku.di.Injection
import com.example.tanahku.ui.auth.login.LoginViewModel
import com.example.tanahku.ui.auth.register.RegisterViewModel
import com.example.tanahku.ui.classify.ClassifyViewModel
import com.example.tanahku.ui.crops.CropsViewModel
import com.example.tanahku.ui.detail.DetailViewModel
import com.example.tanahku.ui.soils.SoilViewModel

class ViewModelFactory(private val repository: TanahkuRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ClassifyViewModel::class.java) -> {
                ClassifyViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CropsViewModel::class.java) -> {
                CropsViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SoilViewModel::class.java) -> {
                SoilViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}