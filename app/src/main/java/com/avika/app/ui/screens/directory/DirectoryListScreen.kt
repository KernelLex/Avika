package com.avika.app.ui.screens.directory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.avika.app.data.model.Clinic
import com.avika.app.data.model.Specialty
import com.avika.app.ui.components.AvikaTopBar
import com.avika.app.ui.components.ClinicCard

@Composable
fun DirectoryListScreen(
    clinics: List<Clinic>,
    onClinicClick: (Clinic) -> Unit,
) {
    var selectedSpecialty by remember { mutableStateOf<Specialty?>(null) }
    val filtered = remember(selectedSpecialty, clinics) {
        if (selectedSpecialty == null) clinics
        else clinics.filter { selectedSpecialty in it.specialties }
    }

    Scaffold(topBar = { AvikaTopBar(title = "Clinic Directory") }) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
        ) {
            item {
                Text(
                    "Compiled from public listings, Aug 2026 — please call ahead to confirm details.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 4.dp),
                )
            }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    item {
                        FilterChip(
                            selected = selectedSpecialty == null,
                            onClick = { selectedSpecialty = null },
                            label = { Text("All") },
                        )
                    }
                    items(Specialty.entries) { specialty ->
                        FilterChip(
                            selected = selectedSpecialty == specialty,
                            onClick = {
                                selectedSpecialty = if (selectedSpecialty == specialty) null else specialty
                            },
                            label = { Text(specialty.label) },
                        )
                    }
                }
            }
            items(filtered, key = { it.id }) { clinic ->
                ClinicCard(clinic = clinic, onClick = { onClinicClick(clinic) })
            }
        }
    }
}
