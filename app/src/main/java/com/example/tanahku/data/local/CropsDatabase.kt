package com.example.tanahku.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CropsEntity::class, SoilEntity::class], version = 2, exportSchema = true)
abstract class CropsDatabase : RoomDatabase() {
    abstract fun cropsDao(): CropsDao

    companion object {
        @Volatile
        private var INSTANCE: CropsDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): CropsDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CropsDatabase::class.java, "crops_database"
                ).fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}

