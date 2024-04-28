package com.dicoding.animalkuiz.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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

	@field:SerializedName("is_owned")
	val isOwned: Boolean? = null,

	@field:SerializedName("step")
	val step: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("animal_type")
	val animalType: AnimalType? = null,

	@field:SerializedName("real_image")
	val realImage: String? = null,

	@field:SerializedName("silhouette_image")
	val silhouetteImage: String? = null,

	@field:SerializedName("badge_image")
	val badgeImage: String? = null
) : Parcelable
