package com.example.dogs_breeds.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dogs_breeds.model.local_breeds.BreedsDao
import com.example.dogs_breeds.model.local_breeds.BreedsEntity
import com.example.dogs_breeds.model.remote_breeds.DogsBreeds
import com.example.dogs_breeds.model.remote_breeds.RetrofitClient

class BreedsRepository (private val breedsDao: BreedsDao) {
    private val retrofitClient = RetrofitClient.getRetrofit()
    val dataFromInternet = MutableLiveData<List<DogsBreeds>>()
    val listAllBreeds: LiveData<List<BreedsEntity>> = breedsDao.getAllBreeds()

    fun convertedData(dogsBreeds: DogsBreeds): List<BreedsEntity> {
        val listado = mutableListOf<BreedsEntity>()

        dogsBreeds.listBreeds.map {
            listado.add(BreedsEntity(it))
        }
        return listado
    }
    suspend fun fetchBreedsData() {
        try {
            val response = retrofitClient.fetchBreedsData()
            when (response.code()) {
                in 200..299 -> response.body()?.let { breedsDao.insertAllBreeds(convertedData(it)) }
                in 300..301 -> Log.d("REPI", "${response.code()} --- ${response.errorBody()}")
                else -> Log.d("REPA", "${response.code()} --- ${response.errorBody()}")
            }
        } catch (t: Throwable) {
            Log.e("REPE", "${t.message}")
        }
    }
}