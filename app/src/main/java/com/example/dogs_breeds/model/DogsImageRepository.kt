package com.example.dogs_breeds.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dogs_breeds.model.local_breeds.DogsImageDao
import com.example.dogs_breeds.model.local_breeds.DogsImageEntity
import com.example.dogs_breeds.model.remote_breeds.DogsImage
import com.example.dogs_breeds.model.remote_breeds.RetrofitClient

class DogsImageRepository (private  val dogsImageDao: DogsImageDao) {
    private val retrofitClient = RetrofitClient.getRetrofit()
    val dataFromInternet = MutableLiveData<List<DogsImage>>()

    fun abc (breeds: String):LiveData<List<DogsImageEntity>>{
         return dogsImageDao.getBreedByBreed(breeds)
    }

    fun convertedImage(breedsImage: DogsImage, breeds: String): List<DogsImageEntity> {
        val listado = mutableListOf<DogsImageEntity>()

        breedsImage.urlImage.map {
            listado.add(DogsImageEntity(it, breeds))
            Log.d("REPOS", "10")
        }
        return listado
    }
    suspend fun fetchImageDogsData( breeds:String) {
        try {
            val response = retrofitClient.fetchImageDogsData(breeds)
            Log.d("REPOS", "7")
            when (response.code()) {
                in 200..299 -> response.body()?.let { dogsImageDao.insertAllDogsImages(convertedImage(it,breeds)) }
                in 300..301 -> Log.d("REPO", "${response.code()} --- ${response.errorBody()}")
                else -> Log.d("REPU", "${response.code()} --- ${response.errorBody()}")
            }
        } catch (t: Throwable) {
            Log.e("REPA", "${t.message}")
        }
    }


}