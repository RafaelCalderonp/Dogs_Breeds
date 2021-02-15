package com.example.dogs_breeds.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.dogs_breeds.model.DogsImageRepository
import com.example.dogs_breeds.model.local_breeds.DogsImageDataBase
import com.example.dogs_breeds.model.local_breeds.DogsImageEntity
import com.example.dogs_breeds.model.remote_breeds.DogsImage
import kotlinx.coroutines.launch

class DogsImageViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: DogsImageRepository
    val livedataFromInternet: LiveData<List<DogsImage>>
    var breed= "-1"



    fun getDogsById(id: String) =viewModelScope.launch{
       breed = id
        repository.fetchImageDogsData(breed)
    }
    fun getImages():LiveData<List<DogsImageEntity>> = repository.abc(breed)



    init {

        Log.d("REPart", "2 $breed")
        val dogsImageDao = DogsImageDataBase.getDataBase(application).getImageDogsDao()
        repository = DogsImageRepository(dogsImageDao)

        livedataFromInternet = repository.dataFromInternet
    }
}