package com.example.dogs_breeds.model.local_breeds

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "breeds_table")
data class BreedsEntity (
    @PrimaryKey
    @NotNull
    val breed : String

)