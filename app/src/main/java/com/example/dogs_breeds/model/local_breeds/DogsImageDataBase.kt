package com.example.dogs_breeds.model.local_breeds

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DogsImageEntity::class], version = 1)
abstract class DogsImageDataBase : RoomDatabase() {

    abstract fun getImageDogsDao(): DogsImageDao
    companion object{
        @Volatile
        private var INSTANCE : DogsImageDataBase? = null

        fun getDataBase(context: Context) : DogsImageDataBase {
            val tempInstace = INSTANCE
            if (tempInstace != null) {
                return tempInstace
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogsImageDataBase::class.java, "dogs_Image_db"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}