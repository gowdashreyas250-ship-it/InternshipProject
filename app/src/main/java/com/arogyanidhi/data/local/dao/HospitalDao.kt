package com.arogyanidhi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arogyanidhi.data.local.entity.Hospital
import kotlinx.coroutines.flow.Flow

@Dao
interface HospitalDao {
    @Query("SELECT * FROM hospitals")
    fun getAllHospitals(): Flow<List<Hospital>>

    @Query("SELECT * FROM hospitals WHERE name LIKE '%' || :query || '%' OR address LIKE '%' || :query || '%'")
    fun searchHospitals(query: String): Flow<List<Hospital>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHospitals(hospitals: List<Hospital>)
}
