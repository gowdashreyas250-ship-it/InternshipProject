package com.arogyanidhi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schemes")
data class Scheme(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val eligibility: String,
    val benefits: String,
    val requiredDocuments: String,
    val category: String // Central, State, etc.
)
