package com.example.dogs_breeds.model.remote_breeds

import com.google.gson.annotations.SerializedName

data class DogsBreeds (
    @SerializedName("message")
    val listBreeds: List<String>
)

