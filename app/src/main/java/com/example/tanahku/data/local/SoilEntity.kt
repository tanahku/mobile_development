package com.example.tanahku.data.local

import androidx.compose.ui.graphics.painter.Painter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "soils")
data class SoilEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "englishName")
    val englishName: String,

    @ColumnInfo(name = "description")
    val desc: String,

    @ColumnInfo(name = "image")
    val image: Int
)
