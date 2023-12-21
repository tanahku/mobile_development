package com.example.tanahku.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "crops")
data class CropsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "latinName")
    val latinName: String,

    @ColumnInfo(name = "soil")
    val soil: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "desc")
    val desc: String
) : Parcelable
