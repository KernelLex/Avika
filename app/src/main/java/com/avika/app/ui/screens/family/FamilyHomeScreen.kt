package com.avika.app.ui.screens.family

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.avika.app.data.model.ChildProfile
import com.avika.app.data.model.Family
import com.avika.app.ui.components.AvikaTopBar
import com.avika.app.ui.components.IconChip
import com.avika.app.ui.theme.AvikaPlum
import com.avika.app.ui.theme.AvikaPlumLight

@Composable
fun FamilyHomeScreen(
    family: Family,
    children: List<ChildProfile>,
    onBack: () -> Unit,
    onAddChild: () -> Unit,
    onOpenChildIntake: (ChildProfile) -> Unit,
    onSignOut: () -> Unit,
) {
    val clipboard: ClipboardManager = LocalClipboardManager.current

    Scaffold(topBar = { AvikaTopBar(title = family.name.ifBlank { "Your family" }, onBack = onBack) }) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth().padding(padding),
        ) {
            item {
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Invite code", style = MaterialTheme.typography.titleMedium)
                            Text(
                                "Share with your partner or other family members so they can join",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Text(
                                family.inviteCode,
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.padding(top = 6.dp),
                            )
                        }
                        IconButton(onClick = { clipboard.setText(AnnotatedString(family.inviteCode)) }) {
                            Icon(Icons.Filled.ContentCopy, contentDescription = "Copy invite code")
                        }
                    }
                }
            }
            item {
                Text("Children", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 4.dp))
            }
            items(children, key = { it.id }) { child ->
                Card(
                    onClick = { onOpenChildIntake(child) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        IconChip(
                            icon = Icons.Filled.Person,
                            containerColor = AvikaPlumLight,
                            contentColor = AvikaPlum,
                            size = 44.dp,
                            modifier = Modifier.padding(end = 14.dp),
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(child.name, style = MaterialTheme.typography.titleMedium)
                            Text(
                                "Tell us about ${child.name.ifBlank { "them" }}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
            item {
                OutlinedButton(onClick = onAddChild, modifier = Modifier.fillMaxWidth()) {
                    Text("Add another child")
                }
            }
            item {
                TextButton(onClick = onSignOut, modifier = Modifier.fillMaxWidth()) {
                    Text("Sign out")
                }
            }
        }
    }
}
