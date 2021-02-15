package com.example.dogs_breeds.model.remote_breeds

import com.google.gson.annotations.SerializedName

data class DogsImage (
    @SerializedName("message")
    val urlImage: List<String>
)