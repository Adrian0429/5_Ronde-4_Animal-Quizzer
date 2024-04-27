package com.dicoding.animalkuiz.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetInventory(

	@field:SerializedName("data")
	val data: List<DataItemInventory?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class AnimalType(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable

@Parcelize
data class DataItemInventory(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("step")
	val step: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("animal_type")
	val animalType: AnimalType? = null
) : Parcelable
