package com.example.dogs_breeds.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.dogs_breeds.model.BreedsRepository
import com.example.dogs_breeds.model.local_breeds.BreedsDataBase
import com.example.dogs_breeds.model.local_breeds.BreedsEntity
import com.example.dogs_breeds.model.remote_breeds.DogsBreeds
import kotlinx.coroutines.launch

class BreedsViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: BreedsRepository
    val livedataFromInternet: LiveData<List<DogsBreeds>>
    val allDogs: LiveData<List<BreedsEntity>>

    init {

        val breedsDao = BreedsDataBase.getDataBase(application).getBreedsDao()
        repository = BreedsRepository(breedsDao)
        allDogs = repository.listAllBreeds
        viewModelScope.launch {
            repository.fetchBreedsData()
        }
        livedataFromInternet = repository.dataFromInternet

    }
}