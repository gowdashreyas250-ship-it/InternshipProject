package com.arogyanidhi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hospitals")
data class Hospital(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val address: String,
    val contact: String,
    val type: String, // Government, Private
    val facilities: String,
    val latitude: Double,
    val longitude: Double
)
