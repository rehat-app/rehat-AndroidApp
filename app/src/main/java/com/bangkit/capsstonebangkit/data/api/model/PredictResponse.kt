package com.bangkit.capsstonebangkit.data.api.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PredictResponse(
    @SerializedName("rescode")
    val rescode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("prediction")
    val prediction: Prediction
): Parcelable {
    @Parcelize
    data class Prediction(
        @SerializedName("image")
        val image: String,
        @SerializedName("index_eyebag")
        val indexEyebag: Int,
        @SerializedName("index_eyelid")
        val indexEyelid: Int,
        @SerializedName("prob_eyebag")
        val probEyebag: Double,
        @SerializedName("prob_eyelid")
        val probEyelid: Double,
        @SerializedName("eyebagCondition")
        val eyebagCondition: String,
        @SerializedName("eyelidCondition")
        val eyelidCondition: String,
        @SerializedName("finalCondition")
        val finalCondition: FinalCondition
    ): Parcelable {
        @Parcelize
        data class FinalCondition(
            @SerializedName("probability")
            val probability: Double,
            @SerializedName("header")
            val header: String,
            @SerializedName("detail")
            val detail: String
        ): Parcelable
    }
}