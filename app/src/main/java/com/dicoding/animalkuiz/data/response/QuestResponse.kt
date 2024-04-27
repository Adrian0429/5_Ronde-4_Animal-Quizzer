package com.dicoding.animalkuiz.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetAllUserQuest(

	@field:SerializedName("data")
	val data: List<DataItemQuest?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class DataItemQuest(

	@field:SerializedName("step")
	val step: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("animal_type")
	val animalType: AnimalTypeQuest? = null
) : Parcelable

@Parcelize
data class AnimalTypeQuest(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable


@Parcelize
data class UserQuestAdvance(

	@field:SerializedName("data")
	val data: DataQuestAdvance? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable


@Parcelize
data class DataQuestAdvance(

	@field:SerializedName("step")
	val step: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("animal_type")
	val animalType: AnimalTypeQuest? = null
) : Parcelable

@Parcelize
data class MintaQuestData(

	@field:SerializedName("hint")
	val hint: String? = null,

	@field:SerializedName("step")
	val step: Int? = null,

	@field:SerializedName("silhouette_image")
	val silhouetteImage: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("animal_type")
	val animalType: AnimalType? = null
) : Parcelable

@Parcelize
data class MintaquestResponse(

	@field:SerializedName("data")
	val data: MintaQuestData? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable
