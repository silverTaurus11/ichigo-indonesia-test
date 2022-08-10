package com.project.myapplication.model.photo

import com.google.gson.annotations.SerializedName
import com.project.myapplication.model.url.Url
import com.project.myapplication.model.user.User

data class Photo(
    @SerializedName("id") val id: String,
    @SerializedName("description") val description: String,
    @SerializedName("user") val user: User,
    @SerializedName("urls") val urls: Url
)