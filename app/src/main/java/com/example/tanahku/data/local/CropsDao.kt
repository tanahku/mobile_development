package com.example.tanahku.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface CropsDao {

    @Query("SELECT * FROM crops")
    fun getAllCrops(): List<CropsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCrops(crops: List<CropsEntity>)

    @Query("SELECT * FROM crops WHERE soil = :soil")
    fun getCropsBySoil(soil: String): List<CropsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSoil(soil: List<SoilEntity>)

    @Query("SELECT * FROM soils")
    fun getAllSoils(): List<SoilEntity>

    @Query("SELECT * FROM soils WHERE id = :soilId")
    fun getSoilById(soilId: Long): SoilEntity
    @Query("SELECT * FROM crops WHERE id = :cropId")
    fun getCropById(cropId: Int): CropsEntity
}