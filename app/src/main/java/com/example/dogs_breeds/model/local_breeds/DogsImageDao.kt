package com.example.dogs_breeds.model.local_breeds

import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface DogsImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogsImage(dogsImage : DogsImageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDogsImages(listDogsImage: List<DogsImageEntity>)

    @Update
    suspend fun updateDogsImage(dogsImage: DogsImageEntity)

    @Delete
    suspend fun deleteDogsImage(DogsImage: DogsImageEntity)

    @Query("DELETE FROM dogs_image_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM dogs_image_table ORDER BY breed DESC")
    fun getAllDogsImages() : LiveData<List<DogsImageEntity>>

    @Query("SELECT * FROM dogs_image_table WHERE breed = :id")
    fun getBreedByBreed(id: String) : LiveData<List<DogsImageEntity>>

}