package com.arogyanidhi.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.arogyanidhi.ui.navigation.Screen
import com.arogyanidhi.ui.theme.CardEligibility
import com.arogyanidhi.ui.theme.EligibilityPrimary
import com.arogyanidhi.ui.theme.EligibilityAccent

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val nearbyHospitals = listOf(
        HospitalShortInfo("Victoria Hospital", "Government", "2.1 km", "080-26701150"),
        HospitalShortInfo("Manipal Hospital", "Private", "3.5 km", "080-22221111"),
        HospitalShortInfo("NIMHANS", "Government", "4.2 km", "080-26995000"),
        HospitalShortInfo("Fortis Hospital", "Private", "5.0 km", "080-66214444")
    )

    Scaffold(
        topBar = { HomeTopBar(navController) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    EligibilityCheckerCard(navController, modifier = Modifier.fillMaxWidth())
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Nearby Hospitals",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { navController.navigate(Screen.Hospitals.route) }) {
                        Text("See All", fontSize = 12.sp)
                    }
                }
                
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    items(nearbyHospitals) { hospital ->
                        HospitalShortcutCard(
                            name = hospital.name,
                            type = hospital.type,
                            distance = hospital.distance,
                            onClick = { navController.navigate(Screen.Hospitals.route) },
                            onCallClick = {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:${hospital.contact}")
                                }
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            }
            // ... (rest of the code stays same)
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    MiniActionCard(
                        title = "Explore Schemes",
                        icon = Icons.Default.Explore,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate(Screen.Schemes.route) }
                    )
                    MiniActionCard(
                        title = "Arogya Mitra",
                        icon = Icons.Default.SmartToy,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate(Screen.Chatbot.route) }
                    )
                }
            }
            
            item {
                Text(
                    text = "Other Services",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    MiniActionCard(
                        title = "Emergency",
                        icon = Icons.Default.Emergency,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate(Screen.Emergency.route) }
                    )
                    MiniActionCard(
                        title = "First Aid",
                        icon = Icons.Default.MedicalServices,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate(Screen.FirstAid.route) }
                    )
                    MiniActionCard(
                        title = "Health Tips",
                        icon = Icons.Default.TipsAndUpdates,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate(Screen.HealthTips.route) }
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

data class HospitalShortInfo(val name: String, val type: String, val distance: String, val contact: String)

@Composable
fun HospitalShortcutCard(name: String, type: String, distance: String, onClick: () -> Unit, onCallClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(130.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.LocalHospital, null, tint = Color(0xFF00695C), modifier = Modifier.size(24.dp))
                IconButton(
                    onClick = onCallClick,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(Icons.Default.Call, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(name, fontWeight = FontWeight.Bold, fontSize = 13.sp, maxLines = 1)
            Text(type, fontSize = 11.sp, color = Color.Gray)
            Text(distance, fontSize = 10.sp, color = MaterialTheme.colorScheme.primary)
        }
    }
}


@Composable
fun HomeTopBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.Favorite, 
                contentDescription = null, 
                tint = Color.Red,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "AROGYA-Nidhi",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                Icon(Icons.Default.AccountCircle, contentDescription = "Profile", modifier = Modifier.size(28.dp))
            }
            IconButton(onClick = { /* Notifications */ }) {
                Icon(Icons.Default.Notifications, contentDescription = "Notifications")
            }
            IconButton(onClick = { /* Search */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }
    }
}

@Composable
fun EligibilityCheckerCard(navController: NavHostController, modifier: Modifier = Modifier) {
    var currentStep by remember { mutableIntStateOf(1) }
    var occupation by remember { mutableStateOf("Farmer") }
    var income by remember { mutableFloatStateOf(0.2f) }
    var selectedState by remember { mutableStateOf("Karnataka") }
    var eligibleScheme by remember { mutableStateOf<String?>(null) }
    
    var expandedOccupation by remember { mutableStateOf(false) }
    var expandedState by remember { mutableStateOf(false) }
    
    val occupations = listOf("Farmer", "Laborer", "Self Employed", "Student", "Unemployed")
    val states = listOf("Karnataka", "Delhi", "Maharashtra", "Tamil Nadu", "Kerala")
    
    val displayIncome = (income * 1000000).toInt()

    Card(
        modifier = modifier
            .animateContentSize()
            .clickable { /* Main card click */ },
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = CardEligibility),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        "ELIGIBILITY CHECKER", 
                        fontWeight = FontWeight.ExtraBold, 
                        fontSize = 15.sp,
                        color = EligibilityPrimary,
                        letterSpacing = 1.sp
                    )
                    Text(
                        if (eligibleScheme == null) "Step $currentStep of 3" else "Verification Complete", 
                        fontSize = 11.sp, 
                        color = EligibilityAccent.copy(alpha = 0.8f)
                    )
                }
                Icon(
                    Icons.Default.VerifiedUser, 
                    null, 
                    tint = EligibilityPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            if (eligibleScheme == null) {
                Spacer(modifier = Modifier.height(16.dp))
                
                when (currentStep) {
                    1 -> {
                        // Step 1: Occupation & State
                        Text("STEP 1: PROFILE DETAILS", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = EligibilityPrimary)
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        // Occupation Dropdown
                        Box {
                            Column {
                                Text("Your Occupation", fontSize = 11.sp, color = Color.DarkGray, fontWeight = FontWeight.Medium)
                                OutlinedTextField(
                                    value = occupation,
                                    onValueChange = {},
                                    modifier = Modifier.fillMaxWidth().height(52.dp).padding(top = 2.dp),
                                    readOnly = true,
                                    trailingIcon = { 
                                        IconButton(onClick = { expandedOccupation = true }) {
                                            Icon(Icons.Default.ArrowDropDown, null, tint = EligibilityPrimary)
                                        }
                                    },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.White,
                                        focusedContainerColor = Color.White,
                                        unfocusedBorderColor = Color.Transparent,
                                        focusedBorderColor = EligibilityPrimary
                                    ),
                                    textStyle = MaterialTheme.typography.bodyMedium
                                )
                            }
                            DropdownMenu(expanded = expandedOccupation, onDismissRequest = { expandedOccupation = false }) {
                                occupations.forEach { occ ->
                                    DropdownMenuItem(text = { Text(occ) }, onClick = { occupation = occ; expandedOccupation = false })
                                }
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(12.dp))

                        // State Selection Dropdown
                        Box {
                            Column {
                                Text("Select State", fontSize = 11.sp, color = Color.DarkGray, fontWeight = FontWeight.Medium)
                                OutlinedTextField(
                                    value = selectedState,
                                    onValueChange = {},
                                    modifier = Modifier.fillMaxWidth().height(52.dp).padding(top = 2.dp),
                                    readOnly = true,
                                    trailingIcon = { 
                                        IconButton(onClick = { expandedState = true }) {
                                            Icon(Icons.Default.Map, null, tint = EligibilityPrimary, modifier = Modifier.size(18.dp))
                                        }
                                    },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.White,
                                        focusedContainerColor = Color.White,
                                        unfocusedBorderColor = Color.Transparent,
                                        focusedBorderColor = EligibilityPrimary
                                    ),
                                    textStyle = MaterialTheme.typography.bodyMedium
                                )
                            }
                            DropdownMenu(expanded = expandedState, onDismissRequest = { expandedState = false }) {
                                states.forEach { state ->
                                    DropdownMenuItem(text = { Text(state) }, onClick = { selectedState = state; expandedState = false })
                                }
                            }
                        }
                    }
                    2 -> {
                        // Step 2: Income
                        Text("STEP 2: FINANCIAL DETAILS", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = EligibilityPrimary)
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Annual Family Income", fontSize = 11.sp, color = Color.DarkGray, fontWeight = FontWeight.Medium)
                            Text("₹${String.format("%,d", displayIncome)}", fontSize = 12.sp, color = EligibilityPrimary, fontWeight = FontWeight.Bold)
                        }
                        Slider(
                            value = income, 
                            onValueChange = { income = it },
                            colors = SliderDefaults.colors(
                                thumbColor = EligibilityPrimary,
                                activeTrackColor = EligibilityPrimary,
                                inactiveTrackColor = EligibilityPrimary.copy(alpha = 0.2f)
                            )
                        )
                        Text("Slide to adjust your annual income for accurate results.", fontSize = 10.sp, color = Color.Gray)
                    }
                    3 -> {
                        // Step 3: Verification
                        Text("STEP 3: FINAL VERIFICATION", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = EligibilityPrimary)
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.5f)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp).fillMaxWidth()) {
                                VerificationDetail("Occupation", occupation)
                                VerificationDetail("State", selectedState)
                                VerificationDetail("Income", "₹${String.format("%,d", displayIncome)}")
                            }
                        }
                        Text(
                            "Please ensure the above details are correct before proceeding for scheme matching.",
                            fontSize = 10.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (currentStep > 1) {
                        OutlinedButton(
                            onClick = { currentStep-- },
                            modifier = Modifier.weight(1f).height(48.dp),
                            shape = RoundedCornerShape(14.dp),
                            border = BorderStroke(1.dp, EligibilityPrimary)
                        ) {
                            Text("Back", color = EligibilityPrimary)
                        }
                    }
                    
                    Button(
                        onClick = { 
                            if (currentStep < 3) {
                                currentStep++
                            } else {
                                eligibleScheme = when {
                                    displayIncome < 50000 && selectedState == "Karnataka" -> "Arogya Karnataka (BPL)"
                                    displayIncome < 50000 -> "Ayushman Bharat PM-JAY"
                                    occupation == "Farmer" && selectedState == "Karnataka" -> "Yeshasvini Health Insurance"
                                    else -> "General Health Schemes"
                                }
                            }
                        },
                        modifier = Modifier.weight(2f).height(48.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = EligibilityPrimary)
                    ) {
                        Text(
                            if (currentStep < 3) "Next Step" else "Verify & Show Scheme", 
                            fontSize = 14.sp, 
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            } else {
                // Result View
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.Celebration, 
                        null, 
                        tint = EligibilityPrimary, 
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "CONGRATULATIONS!", 
                        fontWeight = FontWeight.Bold, 
                        color = EligibilityPrimary,
                        fontSize = 18.sp
                    )
                    Text(
                        "Based on your 3-step verification, you are eligible for:", 
                        fontSize = 13.sp, 
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Surface(
                        color = EligibilityPrimary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = eligibleScheme!!,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = EligibilityPrimary
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    TextButton(onClick = { 
                        eligibleScheme = null
                        currentStep = 1
                    }) {
                        Text("Restart Check", color = EligibilityPrimary)
                    }
                }
            }
        }
    }
}

@Composable
fun VerificationDetail(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontSize = 11.sp, color = Color.Gray)
        Text(value, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
    }
}

@Composable
fun MiniActionCard(title: String, icon: ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, null, modifier = Modifier.size(32.dp), tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, fontSize = 12.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        }
    }
}
