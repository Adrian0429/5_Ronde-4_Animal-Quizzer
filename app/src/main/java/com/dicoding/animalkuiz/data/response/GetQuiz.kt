package com.dicoding.animalkuiz.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetQuiz(

	@field:SerializedName("data")
	val data: List<DataItemQuiz?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class DataItemQuiz(

	@field:SerializedName("wrong_answers")
	val wrongAnswers: List<String?>? = null,

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("correct_answer")
	val correctAnswer: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable
