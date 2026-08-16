package com.avika.app.ui.screens.schemes

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.avika.app.data.model.SchemeSection
import com.avika.app.ui.components.AvikaTopBar
import androidx.compose.material3.Scaffold

@Composable
fun SchemesScreen(sections: List<SchemeSection>) {
    val context = LocalContext.current

    Scaffold(topBar = { AvikaTopBar(title = "Schemes & UDID Guide") }) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
        ) {
            item {
                Text(
                    "General orientation only, not legal or medical advice — always confirm details on the official portal linked in each section.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            items(sections.size) { index ->
                val section = sections[index]
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(section.title, style = MaterialTheme.typography.titleLarge)
                        Text(
                            section.summary,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp, bottom = 10.dp),
                        )
                        section.details.forEach { detail ->
                            Text(
                                "•  $detail",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 6.dp),
                            )
                        }
                        if (section.officialLink != null) {
                            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                            TextButton(onClick = {
                                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(section.officialLink)))
                            }) {
                                Icon(Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
                                Text("Official portal")
                            }
                        }
                    }
                }
            }
        }
    }
}
