package com.example.dogs_breeds.model.remote_breeds

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsBreedsAPI {

        @GET("breeds/list/")
        suspend fun fetchBreedsData() : Response<DogsBreeds>

        @GET("breed/{breed}/images")
        suspend fun fetchImageDogsData(@Path("breed") breed: String) : Response<DogsImage>
}