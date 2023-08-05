package com.example.marvelapp.framework.network.response

import com.google.gson.annotations.SerializedName

// Serilalized Name is an annotation we must use if we want to safely ofuscate and shrink resources

data class DataWrapperResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val data: DataContainerResponse
)
