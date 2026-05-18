package com.arogyanidhi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arogyanidhi.data.local.entity.Scheme
import kotlinx.coroutines.flow.Flow

@Dao
interface SchemeDao {
    @Query("SELECT * FROM schemes")
    fun getAllSchemes(): Flow<List<Scheme>>

    @Query("SELECT * FROM schemes WHERE name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchSchemes(query: String): Flow<List<Scheme>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchemes(schemes: List<Scheme>)
}
