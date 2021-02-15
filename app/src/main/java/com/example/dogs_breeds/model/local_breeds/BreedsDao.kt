package com.example.dogs_breeds.model.local_breeds

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BreedsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreed(breed : BreedsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBreeds(listBreeds: List<BreedsEntity>)

    @Update
    suspend fun updateBreed(breed: BreedsEntity)

    @Delete
    suspend fun deleteBreed(breed: BreedsEntity)

    @Query("DELETE FROM breeds_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM breeds_table ORDER BY breed DESC")
    fun getAllBreeds() : LiveData<List<BreedsEntity>>

    @Query("SELECT * FROM Breeds_table WHERE breed = :id")
    fun getBreedByID(id: String) : LiveData<BreedsEntity>

}