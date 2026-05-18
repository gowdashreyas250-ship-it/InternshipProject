package com.arogyanidhi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstAidScreen(navController: NavHostController) {
    val firstAidTopics = listOf(
        FirstAidTopic(
            "CPR (Cardiopulmonary Resuscitation)",
            "1. Push hard and fast in the center of the chest.\n2. Tilt the head back and lift the chin to open the airway.\n3. Give rescue breaths if trained.\n4. Continue until professional help arrives."
        ),
        FirstAidTopic(
            "Burns",
            "1. Cool the burn under cold running water for at least 20 minutes.\n2. Do not use ice, butter, or ointments.\n3. Cover the burn loosely with plastic wrap or a sterile dressing.\n4. Seek medical attention for severe burns."
        ),
        FirstAidTopic(
            "Fractures",
            "1. Do not move the person unless necessary.\n2. Stop any bleeding by applying pressure.\n3. Immobilize the injured area with a splint or sling.\n4. Apply ice packs to reduce swelling."
        ),
        FirstAidTopic(
            "Choking",
            "1. Give up to 5 back blows between the shoulder blades.\n2. If not cleared, give up to 5 abdominal thrusts (Heimlich maneuver).\n3. Repeat until the object is forced out or the person becomes unconscious."
        ),
        FirstAidTopic(
            "Bleeding",
            "1. Apply direct pressure to the wound with a clean cloth.\n2. Maintain pressure until bleeding stops.\n3. Clean the wound with water and apply a sterile bandage.\n4. Seek help if bleeding is heavy or doesn't stop."
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("First Aid Guide") },
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
                    text = "Basic instructions for common medical emergencies. Always call for professional help first.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(firstAidTopics) { topic ->
                FirstAidCard(topic)
            }
        }
    }
}

data class FirstAidTopic(val title: String, val instructions: String)

@Composable
fun FirstAidCard(topic: FirstAidTopic) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Info, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = topic.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = topic.instructions,
                style = MaterialTheme.typography.bodySmall,
                lineHeight = 20.sp
            )
        }
    }
}
