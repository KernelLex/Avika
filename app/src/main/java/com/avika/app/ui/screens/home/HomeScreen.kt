package com.avika.app.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Park
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.avika.app.ui.components.AvikaTopBar

private data class HomeAction(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val onClick: () -> Unit,
)

@Composable
fun HomeScreen(
    onOpenDirectory: () -> Unit,
    onOpenVenues: () -> Unit,
    onOpenSchemes: () -> Unit,
) {
    val actions = listOf(
        HomeAction(
            "Find a clinic",
            "Occupational, speech & ABA therapy, developmental pediatrics",
            Icons.Filled.LocalHospital,
            onOpenDirectory,
        ),
        HomeAction(
            "Sensory-friendly venues",
            "Parks and public spaces with accessible, inclusive design",
            Icons.Filled.Park,
            onOpenVenues,
        ),
        HomeAction(
            "Schemes & UDID guide",
            "UDID, Swavlamban, Niramaya, railway concessions, pensions",
            Icons.AutoMirrored.Filled.MenuBook,
            onOpenSchemes,
        ),
    )

    Scaffold(topBar = { AvikaTopBar(title = "Avika") }) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
        ) {
            item {
                Text(
                    "A starting point for Bengaluru families",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 4.dp),
                )
                Text(
                    "Find therapy clinics, accessible places to spend time, and the government support your family is entitled to — all in one place.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp),
                )
            }
            items(actions) { action ->
                HomeActionCard(action)
            }
        }
    }
}

@Composable
private fun HomeActionCard(action: HomeAction) {
    Card(
        onClick = action.onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                action.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(end = 16.dp),
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    action.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Text(
                    action.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
            Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}
