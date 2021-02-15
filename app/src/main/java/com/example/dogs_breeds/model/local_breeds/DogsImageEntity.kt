package com.example.dogs_breeds.model.local_breeds

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
@Entity(tableName = "dogs_image_table")
data class DogsImageEntity(
        @PrimaryKey
        @NotNull
        val urlImage : String,
        val breed : String
)