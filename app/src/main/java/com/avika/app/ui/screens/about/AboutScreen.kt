package com.avika.app.ui.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.avika.app.ui.components.AvikaTopBar

@Composable
fun AboutScreen() {
    Scaffold(topBar = { AvikaTopBar(title = "About Avika") }) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
        ) {
            item {
                Text(
                    "Why Avika exists",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    "Bengaluru parents of children with disabilities spend hours piecing together which clinics actually help, which public spaces are workable for their child, and which government schemes they qualify for. That information exists, but it's scattered across word-of-mouth, old forum posts, and offices that are hard to reach. Avika puts it in one place.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
            item {
                Text(
                    "What's in this version",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    "A directory of therapy clinics and developmental pediatricians, a list of public venues in Bengaluru with documented accessible or sensory-friendly features, and a plain-language guide to UDID, Swavlamban, Niramaya, railway concessions, and disability pensions.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
            item {
                Text(
                    "A note on accuracy",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    "Clinic and venue listings are compiled from public sources and have not all been field-verified. Scheme details link to official government portals, which are the source of truth — this app is an orientation guide, not a replacement for them. We're actively working to verify every listing directly with families and providers.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
            item {
                Text(
                    "What's next",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    "Parent reviews, search and filtering, and easier sharing are coming in the next few weeks, along with an ongoing effort to grow and verify the directory with local NGOs and parent communities.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
        }
    }
}
