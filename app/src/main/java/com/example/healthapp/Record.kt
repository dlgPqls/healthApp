package com.example.healthapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    val check : Boolean,
    val content : String,
    val year : Int,
    val month : Int,
    val day : Int
)
