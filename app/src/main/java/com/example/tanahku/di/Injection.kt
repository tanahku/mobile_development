package com.example.tanahku.di

import android.content.Context
import com.example.tanahku.data.TanahkuRepository
import com.example.tanahku.data.local.CropsDatabase
import com.example.tanahku.data.pref.UserPreference
import com.example.tanahku.data.pref.dataStore
import com.example.tanahku.data.remote.ApiConfig
import com.example.tanahku.utils.AppExecutors
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): TanahkuRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        val database = CropsDatabase.getDatabase(context)
        val appExecutors = AppExecutors()
        val cropsDao = database.cropsDao()
        return TanahkuRepository.getInstance(pref, apiService, cropsDao, appExecutors)
    }
}