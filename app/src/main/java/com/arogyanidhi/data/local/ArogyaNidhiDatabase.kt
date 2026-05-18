package com.arogyanidhi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arogyanidhi.data.local.dao.HospitalDao
import com.arogyanidhi.data.local.dao.SchemeDao
import com.arogyanidhi.data.local.entity.Hospital
import com.arogyanidhi.data.local.entity.Scheme

@Database(entities = [Scheme::class, Hospital::class], version = 1, exportSchema = false)
abstract class ArogyaNidhiDatabase : RoomDatabase() {
    abstract fun schemeDao(): SchemeDao
    abstract fun hospitalDao(): HospitalDao
}
