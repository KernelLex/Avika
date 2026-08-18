package com.avika.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.avika.app.ui.components.IconChip
import com.avika.app.ui.theme.AvikaAmber
import com.avika.app.ui.theme.AvikaAmberLight
import com.avika.app.ui.theme.AvikaPlum
import com.avika.app.ui.theme.AvikaPlumLight
import com.avika.app.ui.theme.AvikaTeal
import com.avika.app.ui.theme.AvikaTealDark
import com.avika.app.ui.theme.AvikaTealLight

private data class HomeAction(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val chipColor: androidx.compose.ui.graphics.Color,
    val chipContentColor: androidx.compose.ui.graphics.Color,
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
            AvikaTealLight, AvikaTealDark,
            onOpenDirectory,
        ),
        HomeAction(
            "Sensory-friendly venues",
            "Parks and public spaces with accessible, inclusive design",
            Icons.Filled.Park,
            AvikaAmberLight, AvikaAmber,
            onOpenVenues,
        ),
        HomeAction(
            "Schemes & UDID guide",
            "UDID, Swavlamban, Niramaya, railway concessions, pensions",
            Icons.AutoMirrored.Filled.MenuBook,
            AvikaPlumLight, AvikaPlum,
            onOpenSchemes,
        ),
    )

    Scaffold { padding ->
        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
        ) {
            item { HomeHero() }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(horizontal = 16.dp).padding(top = 20.dp),
                ) {
                    actions.forEach { HomeActionCard(it) }
                }
            }
        }
    }
}

@Composable
private fun HomeHero() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(listOf(AvikaTealDark, AvikaTeal)),
                RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp),
            )
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 28.dp),
    ) {
        Text(
            "Avika",
            style = MaterialTheme.typography.titleMedium,
            color = AvikaTealLight,
        )
        Text(
            "A starting point for Bengaluru families",
            style = MaterialTheme.typography.displaySmall,
            color = androidx.compose.ui.graphics.Color.White,
            modifier = Modifier.padding(top = 8.dp, end = 24.dp),
        )
        Text(
            "Find therapy clinics, accessible places to spend time, and the government support your family is entitled to — all in one place.",
            style = MaterialTheme.typography.bodyLarge,
            color = AvikaTealLight,
            modifier = Modifier.padding(top = 10.dp),
        )
    }
}

@Composable
private fun HomeActionCard(action: HomeAction) {
    Card(
        onClick = action.onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconChip(
                icon = action.icon,
                containerColor = action.chipColor,
                contentColor = action.chipContentColor,
                modifier = Modifier.padding(end = 16.dp),
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(action.title, style = MaterialTheme.typography.titleMedium)
                Text(
                    action.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 2.dp),
                )
            }
            Icon(
                Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
