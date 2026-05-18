package com.arogyanidhi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthTipsScreen(navController: NavHostController) {
    val healthTips = listOf(
        HealthTip("Stay Hydrated", "Drink at least 8-10 glasses of water daily to maintain energy and clear skin.", Color(0xFF03A9F4)),
        HealthTip("Regular Exercise", "Aim for at least 30 minutes of moderate physical activity every day.", Color(0xFF4CAF50)),
        HealthTip("Balanced Diet", "Include more fruits, vegetables, and whole grains in your meals.", Color(0xFFFF9800)),
        HealthTip("Better Sleep", "Ensure 7-9 hours of quality sleep for physical and mental restoration.", Color(0xFF673AB7)),
        HealthTip("Reduce Stress", "Practice mindfulness, meditation, or deep breathing exercises daily.", Color(0xFFE91E63)),
        HealthTip("Check-ups", "Schedule annual health screenings to catch potential issues early.", Color(0xFF795548)),
        HealthTip("Hand Hygiene", "Wash your hands frequently with soap to prevent the spread of infections.", Color(0xFF009688))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daily Health Tips") },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(healthTips) { tip ->
                HealthTipCard(tip)
            }
        }
    }
}

data class HealthTip(val title: String, val content: String, val color: Color)

@Composable
fun HealthTipCard(tip: HealthTip) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(tip.color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.TipsAndUpdates,
                    contentDescription = null,
                    tint = tip.color,
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = tip.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = tip.color
                )
                Text(
                    text = tip.content,
                    style = MaterialTheme.typography.bodySmall,
                    lineHeight = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}
