package com.arogyanidhi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arogyanidhi.data.local.entity.Scheme
import com.arogyanidhi.data.repository.SchemeRepository
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
class SchemesViewModel @Inject constructor(
    private val repository: SchemeRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val schemes: StateFlow<List<Scheme>> = _searchQuery
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                repository.getAllSchemes()
            } else {
                repository.searchSchemes(query)
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
        // Pre-populate some data if needed, or fetch from API
        viewModelScope.launch {
            repository.insertSchemes(getSampleSchemes())
        }
    }

    private fun getSampleSchemes(): List<Scheme> {
        return listOf(
            Scheme(
                name = "Ayushman Bharat PM-JAY",
                description = "World's largest health insurance scheme providing a health cover of Rs. 5 lakhs per family per year for secondary and tertiary care hospitalization.",
                eligibility = "Families listed in the SECC 2011 database, BPL card holders.",
                benefits = "Cashless treatment, coverage for 3 days of pre-hospitalization and 15 days post-hospitalization.",
                requiredDocuments = "Aadhar Card, Ration Card, PM-JAY ID Card.",
                category = "Central"
            ),
            Scheme(
                name = "Arogya Karnataka (SAST)",
                description = "A universal health coverage scheme of Karnataka, integrated with PM-JAY to provide seamless healthcare services.",
                eligibility = "All residents of Karnataka. Divided into 'Eligible' (BPL) and 'General' (APL) categories.",
                benefits = "Cashless treatment for BPL families. General category gets 30% reimbursement of the government rate.",
                requiredDocuments = "Aadhar Card, PDS Ration Card.",
                category = "State (Karnataka)"
            ),
            Scheme(
                name = "Yeshasvini Health Insurance",
                description = "A unique self-funded health insurance scheme for members of co-operative societies in Karnataka.",
                eligibility = "Members of rural or urban co-operative societies for at least 3 months.",
                benefits = "Fixed-price surgeries and OPD benefits in over 500 networked hospitals.",
                requiredDocuments = "Yeshasvini Health Card, Aadhar Card.",
                category = "State (Karnataka)"
            ),
            Scheme(
                name = "Jyothi Sanjeevini",
                description = "A comprehensive healthcare scheme for Karnataka State Government employees and their dependents.",
                eligibility = "Serving State Government Employees and their family members.",
                benefits = "Cashless treatment for tertiary procedures in empaneled private hospitals.",
                requiredDocuments = "Employee Health Card, Government ID.",
                category = "State (Karnataka)"
            ),
            Scheme(
                name = "Vajpayee Arogyashree",
                description = "A health insurance scheme specifically for BPL families in Karnataka for major life-threatening diseases.",
                eligibility = "Members of BPL families in Karnataka.",
                benefits = "Full coverage for 400+ major surgeries including Heart, Kidney, Cancer, and Brain surgeries.",
                requiredDocuments = "BPL Ration Card, Aadhar Card.",
                category = "State (Karnataka)"
            ),
            Scheme(
                name = "Janani Suraksha Yojana (JSY)",
                description = "A safe motherhood intervention under the National Health Mission to reduce maternal and neonatal mortality.",
                eligibility = "All pregnant women from BPL/SC/ST families.",
                benefits = "Financial assistance of Rs. 600 - Rs. 700 for rural and urban institutional deliveries.",
                requiredDocuments = "MCP Card, Bank Account Details, Aadhar Card.",
                category = "Central"
            ),
            Scheme(
                name = "Mathru Poorna",
                description = "A scheme to improve maternal and child health by providing one nutritious full meal daily.",
                eligibility = "Pregnant and lactating mothers registered at Anganwadis.",
                benefits = "One hot cooked meal daily, including supplements like Iron and Folic Acid.",
                requiredDocuments = "Anganwadi Registration, Aadhar Card.",
                category = "State (Karnataka)"
            ),
            Scheme(
                name = "Pradhan Mantri Janaushadhi Pariyojana",
                description = "A campaign launched to provide quality medicines at affordable prices for all.",
                eligibility = "Open to all citizens.",
                benefits = "Generic medicines available at 50% to 90% lower cost than market prices.",
                requiredDocuments = "Doctor's Prescription.",
                category = "Central"
            ),
            Scheme(
                name = "Hrudaya Sanjeevini",
                description = "Specialized scheme for children in Karnataka with heart diseases.",
                eligibility = "Children from BPL families (0-18 years).",
                benefits = "Free treatment and surgery for congenital and other heart defects.",
                requiredDocuments = "BPL Card, Hospital Reference, Aadhar Card.",
                category = "State (Karnataka)"
            ),
            Scheme(
                name = "Rashtriya Bal Swasthya Karyakram (RBSK)",
                description = "A child health screening and early intervention service for 4Ds (Defects, Deficiencies, Diseases, and Developmental delays).",
                eligibility = "Children from birth to 18 years.",
                benefits = "Free treatment, including surgeries for identified health conditions.",
                requiredDocuments = "School/Anganwadi ID, Aadhar Card.",
                category = "Central"
            )
        )
    }
}
