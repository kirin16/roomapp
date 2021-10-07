package com.example.roomapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OneMoreModel (
    val x: Int,
    val y: String
        ) : Parcelable
