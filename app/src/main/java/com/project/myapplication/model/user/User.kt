package com.project.myapplication.model.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: String,
    @SerializedName("username") val userName: String,
    @SerializedName("name") val name: String
)