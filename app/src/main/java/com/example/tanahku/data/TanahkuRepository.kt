package com.example.tanahku.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.tanahku.data.local.CropsDao
import com.example.tanahku.data.local.CropsEntity
import com.example.tanahku.data.local.SoilEntity
import com.example.tanahku.data.local.SoilList
import com.example.tanahku.data.pref.UserModel
import com.example.tanahku.data.pref.UserPreference
import com.example.tanahku.data.remote.ApiConfig
import com.example.tanahku.data.remote.ApiService
import com.example.tanahku.data.remote.Data
import com.example.tanahku.data.remote.DataPlant
import com.example.tanahku.data.remote.LoginResponse
import com.example.tanahku.data.remote.PlantResponse
import com.example.tanahku.data.remote.RegisterResponse
import com.example.tanahku.utils.AppExecutors
import com.example.tanahku.utils.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TanahkuRepository(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
    private val cropsDao: CropsDao,
    private val appExecutors: AppExecutors
) {
    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _plantsResponse = MutableLiveData<List<DataPlant>>()
    val plantsResponse: LiveData<List<DataPlant>> = _plantsResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var testToken: String? = null
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun login() {
        userPreference.login()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun getSoilById(soilId: Long): SoilEntity {
        return withContext(Dispatchers.IO) {
            cropsDao.getSoilById(soilId)
        }
    }
    suspend fun getCropsById(cropId: Int): CropsEntity {
        return withContext(Dispatchers.IO) {
            cropsDao.getCropById(cropId)
        }
    }
    fun registerUser(username: String, email: String, pass: String, callback: (UiState<RegisterResponse>) -> Unit) {
        val token = testToken ?: runBlocking { userPreference.getSession().first().token }
        _isLoading.value = true
        val client = ApiConfig.getApiService(token).register(username, email, pass)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _registerResponse.value = response.body()
                    callback(UiState.Success(response.body()!!))
                } else {
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.body()?.data?.error}"
                    )
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("onFailure: ", t.message.toString())
                callback(UiState.Error(t.message.toString()))
            }

        })
    }

    fun getCropsBySoil(
        soilType: String,
        callback: (UiState<List<CropsEntity>>) -> Unit
    ) {
        val token = testToken ?: runBlocking { userPreference.getSession().first().token }
        _isLoading.value = true
        val client = ApiConfig.getApiService(token).getCropsBySoil(soilType)

        client.enqueue(object : Callback<PlantResponse> {
            override fun onResponse(
                call: Call<PlantResponse>,
                response: Response<PlantResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    val crops = response.body()?.data
                        ?.filter { it.soil.equals(soilType, ignoreCase = true) }
                        ?.map { result ->
                            CropsEntity(
                                id = result.id,
                                name = result.name,
                                latinName = result.latinName,
                                image = result.image,
                                soil = result.soil,
                                desc = result.description
                            )
                        } ?: emptyList()

                    callback(UiState.Success(crops))
                } else {
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.body()?.meta?.message}"
                    )
                    callback(UiState.Error("Failed to fetch data"))
                }
            }

            override fun onFailure(call: Call<PlantResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("onFailure: ", t.message.toString())
                callback(UiState.Error(t.message.toString()))
            }
        })
    }
    fun getAllCrops(callback: (UiState<List<CropsEntity>>) -> Unit) {
        val token = testToken ?: runBlocking { userPreference.getSession().first().token }
        _isLoading.value = true
        val client = ApiConfig.getApiService(token).getAllCrops()
        client.enqueue(object : Callback<PlantResponse> {
            override fun onResponse(
                call: Call<PlantResponse>,
                response: Response<PlantResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    val crops = response.body()?.data?.map { result ->
                        CropsEntity(
                            id = result.id,
                            name = result.name,
                            latinName = result.latinName,
                            image = result.image,
                            soil = result.soil,
                            desc = result.description
                        )
                    } ?: emptyList()

                    callback(UiState.Success(crops))
                } else {
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.body()?.meta?.message}"
                    )
                    callback(UiState.Error("Failed to fetch data"))
                }
            }

            override fun onFailure(call: Call<PlantResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("onFailure: ", t.message.toString())
                callback(UiState.Error("Failed to fetch data"))
            }

        })
    }

    suspend fun saveCropsToDatabase(crops: List<CropsEntity>) {
        withContext(Dispatchers.IO) {
            try {
                cropsDao.insertAllCrops(crops)
            } catch (e: Exception) {
                Log.e(TAG, "Error saving crops to database: ${e.message}")
            }
        }
    }

    suspend fun getCropsFromDatabase(): List<CropsEntity> {
        return withContext(Dispatchers.IO) {
            cropsDao.getAllCrops()
        }
    }

    suspend fun getAllSoils(callback: (UiState<List<SoilEntity>>) -> Unit) {
        try {
            val soils = withContext(Dispatchers.IO) {
                cropsDao.getAllSoils()
            }
            callback(UiState.Success(soils))
        } catch (e: Exception) {
            callback(UiState.Error(e.message.toString()))
        }
    }

    suspend fun insertAllSoils() {
        val soils = SoilList.soils
        cropsDao.insertAllSoil(soils)
    }



    fun loginUser(email: String, pass: String, callback: (UiState<LoginResponse>) -> Unit) {
        val token = testToken ?: runBlocking { userPreference.getSession().first().token }
        _isLoading.value = true
        val client = ApiConfig.getApiService(token).loginUser(email, pass)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    _loginResponse.value = response.body()
                    _loginResponse.postValue(response.body())
                    callback(UiState.Success(response.body()!!))
                } else {
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.body()?.meta?.message}"
                    )
                    callback(UiState.Error("Failed to log in"))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(
                    TAG,
                    "onFailure: ${t.message.toString()}"
                )
                callback(UiState.Error("Failed to log in"))
            }
        })
    }

    companion object {

        const val TAG = "TanahkuRepository"

        @Volatile
        private var instance: TanahkuRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService,
            cropsDao: CropsDao,
            appExecutors: AppExecutors
        ): TanahkuRepository =
            instance ?: synchronized(this) {
                instance ?: TanahkuRepository(userPreference, apiService, cropsDao, appExecutors)
            }.also { instance = it }
    }

}