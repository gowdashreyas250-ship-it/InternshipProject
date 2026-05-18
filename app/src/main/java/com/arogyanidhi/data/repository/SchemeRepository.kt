package com.arogyanidhi.data.repository

import com.arogyanidhi.data.local.dao.SchemeDao
import com.arogyanidhi.data.local.entity.Scheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchemeRepository @Inject constructor(
    private val schemeDao: SchemeDao
) {
    fun getAllSchemes(): Flow<List<Scheme>> = schemeDao.getAllSchemes()

    fun searchSchemes(query: String): Flow<List<Scheme>> = schemeDao.searchSchemes(query)

    suspend fun insertSchemes(schemes: List<Scheme>) = schemeDao.insertSchemes(schemes)
}
