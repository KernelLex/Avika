package com.avika.app.ui.screens.venues

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.avika.app.data.model.Venue
import com.avika.app.ui.components.AvikaTopBar

@Composable
fun VenueDetailScreen(venue: Venue, onBack: () -> Unit) {
    val context = LocalContext.current

    Scaffold(topBar = { AvikaTopBar(title = venue.name, onBack = onBack) }) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
        ) {
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    venue.tags.forEach { tag ->
                        AssistChip(onClick = {}, label = { Text(tag.label) })
                    }
                }
            }
            item {
                Text(venue.description, style = MaterialTheme.typography.bodyLarge)
            }
            item {
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Address", style = MaterialTheme.typography.titleMedium)
                        Text(
                            venue.address,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp),
                        )
                        OutlinedButton(onClick = {
                            val query = Uri.encode("${venue.name}, ${venue.address}")
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$query"))
                            context.startActivity(intent)
                        }) {
                            Icon(Icons.Filled.Map, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
                            Text("Maps")
                        }
                    }
                }
            }
            item {
                HorizontalDivider()
                Text(
                    venue.sourceNote,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
        }
    }
}
