package com.dicoding.animalkuiz.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetAllAnimal(

	@field:SerializedName("data")
	val data: List<DataItemAnimals?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class AnimalsType(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable

@Parcelize
data class DataItemAnimals(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("step")
	val step: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("animal_type")
	val animalType: AnimalsType? = null
) : Parcelable


@Parcelize
data class GetAnimalById(

	@field:SerializedName("data")
	val data: DetailAnimal? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class DetailAnimal(

	@field:SerializedName("habitat")
	val habitat: String? = null,

	@field:SerializedName("hint")
	val hint: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("conservation_status")
	val conservationStatus: String? = null,

	@field:SerializedName("step")
	val step: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("fun_fact")
	val funFact: String? = null,

	@field:SerializedName("animal_type")
	val animalType: AnimalsType? = null,

	@field:SerializedName("food")
	val food: String? = null,

	@field:SerializedName("reproduction")
	val reproduction: String? = null
) : Parcelable

