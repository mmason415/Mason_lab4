package com.example.mmason_lab4.model

import com.google.gson.annotations.SerializedName

data class RandomUserResponse(
    @SerializedName("results") val results: List<User>
)

data class User(
    @SerializedName("login") val login: Login,
    @SerializedName("name") val name: Name,
    @SerializedName("email") val email: String,
    @SerializedName("picture") val picture: Picture
)

data class Login(@SerializedName("uuid") val uuid: String)
data class Name(@SerializedName("first") val first: String, @SerializedName("last") val last: String)
data class Picture(@SerializedName("large") val large: String)
