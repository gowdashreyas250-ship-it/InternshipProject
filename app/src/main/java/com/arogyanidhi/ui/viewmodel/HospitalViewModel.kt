package com.arogyanidhi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arogyanidhi.data.local.entity.Hospital
import com.arogyanidhi.data.repository.HospitalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HospitalViewModel @Inject constructor(
    private val repository: HospitalRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val hospitals: StateFlow<List<Hospital>> = _searchQuery
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                repository.getAllHospitals()
            } else {
                repository.searchHospitals(query)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    init {
        viewModelScope.launch {
            repository.insertHospitals(getSampleHospitals())
        }
    }

    private fun getSampleHospitals(): List<Hospital> {
        return listOf(
            Hospital(
                name = "Victoria Hospital",
                address = "Kalasipalyam, Bengaluru",
                contact = "080-26701150",
                type = "Government",
                facilities = "Emergency, Specialized Surgery, General Medicine",
                latitude = 12.9642,
                longitude = 77.5758
            ),
            Hospital(
                name = "NIMHANS",
                address = "Hosur Road, Bengaluru",
                contact = "080-26995000",
                type = "Government",
                facilities = "Neurology, Psychiatry, Brain Imaging",
                latitude = 12.9431,
                longitude = 77.5968
            ),
            Hospital(
                name = "Manipal Hospital",
                address = "Old Airport Road, Bengaluru",
                contact = "080-22221111",
                type = "Private",
                facilities = "Cardiology, Oncology, Organ Transplant",
                latitude = 12.9593,
                longitude = 77.6444
            ),
            Hospital(
                name = "Fortis Hospital",
                address = "Bannerghatta Road, Bengaluru",
                contact = "080-66214444",
                type = "Private",
                facilities = "Heart Surgery, Orthopedics, Critical Care",
                latitude = 12.8946,
                longitude = 77.5986
            ),
            Hospital(
                name = "St. John's Medical College Hospital",
                address = "Sarjapur Road, Koramangala, Bengaluru",
                contact = "080-22065000",
                type = "Trust/Private",
                facilities = "Multi-specialty, Diagnostics, Emergency",
                latitude = 12.9344,
                longitude = 77.6192
            ),
            Hospital(
                name = "Bowring and Lady Curzon Hospital",
                address = "Shivajinagar, Bengaluru",
                contact = "080-25591325",
                type = "Government",
                facilities = "General Surgery, Maternity, Pediatrics",
                latitude = 12.9841,
                longitude = 77.6033
            ),
            Hospital(
                name = "Aster CMI Hospital",
                address = "Hebbal, Bengaluru",
                contact = "080-43420100",
                type = "Private",
                facilities = "Liver Transplant, Robotics, Emergency",
                latitude = 13.0487,
                longitude = 77.5929
            )
        )
    }
}
