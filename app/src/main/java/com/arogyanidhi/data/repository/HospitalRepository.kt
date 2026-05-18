package com.arogyanidhi.data.repository

import com.arogyanidhi.data.local.dao.HospitalDao
import com.arogyanidhi.data.local.entity.Hospital
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HospitalRepository @Inject constructor(
    private val hospitalDao: HospitalDao
) {
    fun getAllHospitals(): Flow<List<Hospital>> = hospitalDao.getAllHospitals()

    fun searchHospitals(query: String): Flow<List<Hospital>> = hospitalDao.searchHospitals(query)

    suspend fun insertHospitals(hospitals: List<Hospital>) = hospitalDao.insertHospitals(hospitals)
}
