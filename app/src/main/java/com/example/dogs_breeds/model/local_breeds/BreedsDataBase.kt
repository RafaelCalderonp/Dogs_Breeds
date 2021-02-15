package com.example.dogs_breeds.model.local_breeds

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BreedsEntity::class], version = 1)
abstract class BreedsDataBase: RoomDatabase() {

    abstract fun getBreedsDao(): BreedsDao

    companion object {
        @Volatile
        private var INSTANCE : BreedsDataBase? = null

        fun getDataBase(context: Context) : BreedsDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BreedsDataBase::class.java,
                    "breeds_db")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}