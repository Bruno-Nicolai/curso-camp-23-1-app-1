package com.example.marvelapp.presentation.detail

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep //compile kernel to keep this class untouched
@Parcelize //serialize without external libraries
data class DetailViewArg(
    val name: String,
    val imageUrl: String
): Parcelable
