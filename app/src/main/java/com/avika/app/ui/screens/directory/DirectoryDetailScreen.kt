package com.avika.app.ui.screens.directory

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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
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
import com.avika.app.data.model.Clinic
import com.avika.app.ui.components.AvikaTopBar

@Composable
fun DirectoryDetailScreen(clinic: Clinic, onBack: () -> Unit) {
    val context = LocalContext.current

    Scaffold(topBar = { AvikaTopBar(title = clinic.name, onBack = onBack) }) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
        ) {
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    clinic.specialties.forEach {
                        AssistChip(onClick = {}, label = { Text(it.label) })
                    }
                }
            }
            item {
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Address", style = MaterialTheme.typography.titleMedium)
                        Text(
                            clinic.address,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp),
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            if (clinic.phone != null) {
                                Button(onClick = {
                                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${clinic.phone}"))
                                    context.startActivity(intent)
                                }) {
                                    Icon(Icons.Filled.Call, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
                                    Text("Call")
                                }
                            }
                            OutlinedButton(onClick = {
                                val query = Uri.encode("${clinic.name}, ${clinic.address}")
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$query"))
                                context.startActivity(intent)
                            }) {
                                Icon(Icons.Filled.Map, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
                                Text("Maps")
                            }
                        }
                    }
                }
            }
            if (clinic.notes != null) {
                item {
                    HorizontalDivider()
                    Text(
                        clinic.notes,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                }
            }
            item {
                Text(
                    "Info compiled from public listings, Aug 2026 — not independently verified. Please call ahead to confirm before visiting.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
