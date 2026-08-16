package com.avika.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.avika.app.data.model.Clinic
import com.avika.app.data.model.Venue

@Composable
fun ClinicCard(clinic: Clinic, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(clinic.name, style = MaterialTheme.typography.titleMedium)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Place,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(end = 4.dp),
                    )
                    Text(
                        clinic.area,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Row(modifier = Modifier.padding(top = 6.dp)) {
                    clinic.specialties.take(2).forEach {
                        AssistChip(
                            onClick = onClick,
                            label = { Text(it.label, style = MaterialTheme.typography.labelLarge) },
                            modifier = Modifier.padding(end = 6.dp).wrapContentWidth(),
                        )
                    }
                }
            }
            Icon(Icons.Filled.ChevronRight, contentDescription = null)
        }
    }
}

@Composable
fun VenueCard(venue: Venue, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(venue.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    "${venue.category} · ${venue.area}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Icon(Icons.Filled.ChevronRight, contentDescription = null)
        }
    }
}
