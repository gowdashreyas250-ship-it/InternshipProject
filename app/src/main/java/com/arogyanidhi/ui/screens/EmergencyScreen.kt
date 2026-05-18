package com.arogyanidhi.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyScreen(navController: NavHostController) {
    val context = LocalContext.current
    val emergencyContacts = listOf(
        EmergencyContact("Ambulance", "102", Color(0xFFE53935)),
        EmergencyContact("Police", "100", Color(0xFF1E88E5)),
        EmergencyContact("Fire Brigade", "101", Color(0xFFFB8C00)),
        EmergencyContact("Women Helpline", "1091", Color(0xFFD81B60)),
        EmergencyContact("Blood Bank", "1910", Color(0xFF8D6E63)),
        EmergencyContact("Disaster Management", "108", Color(0xFF43A047))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Emergency Contacts") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Quick access to emergency services. Tap to call directly.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            items(emergencyContacts) { contact ->
                EmergencyCard(contact) {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:${contact.number}")
                    }
                    context.startActivity(intent)
                }
            }
        }
    }
}

data class EmergencyContact(val name: String, val number: String, val color: Color)

@Composable
fun EmergencyCard(contact: EmergencyContact, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = contact.color.copy(alpha = 0.1f)),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = contact.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = contact.color
                )
                Text(
                    text = contact.number,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = contact.color
                )
            }
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Call",
                tint = contact.color,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
