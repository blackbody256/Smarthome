package com.example.welcome.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routines")
data class Routine (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  //PK
    val taskName: String,
    val time: String,
    val recurrence: String
)