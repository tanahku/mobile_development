package com.example.tanahku.data.remote

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class PlantResponse(

	@field:SerializedName("data")
	val data: List<DataPlant>,

	@field:SerializedName("meta")
	val meta: MetaPlant
)

data class DataPlant(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("latin_name")
	val latinName: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("soil")
	val soil: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class MetaPlant(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
