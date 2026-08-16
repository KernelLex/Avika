package com.avika.app.ui.screens.venues

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.avika.app.data.model.Venue
import com.avika.app.ui.components.AvikaTopBar
import com.avika.app.ui.components.VenueCard

@Composable
fun VenueListScreen(
    venues: List<Venue>,
    onVenueClick: (Venue) -> Unit,
) {
    Scaffold(topBar = { AvikaTopBar(title = "Sensory-Friendly Venues") }) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
        ) {
            item {
                Text(
                    "Public places in Bengaluru with documented accessible or sensory-friendly features. A short, honest list — we'd rather grow it slowly with verified spots than guess.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            items(venues, key = { it.id }) { venue ->
                VenueCard(venue = venue, onClick = { onVenueClick(venue) })
            }
        }
    }
}
