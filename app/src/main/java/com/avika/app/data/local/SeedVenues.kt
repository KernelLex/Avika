package com.avika.app.data.local

import com.avika.app.data.model.Venue
import com.avika.app.data.model.VenueTag.ACCESSIBLE_RESTROOM
import com.avika.app.data.model.VenueTag.QUIET_SPACE
import com.avika.app.data.model.VenueTag.SENSORY_EQUIPMENT
import com.avika.app.data.model.VenueTag.WHEELCHAIR_ACCESS

/**
 * Compiled from public news/NGO coverage, Aug 2026. These are the venues with
 * documented, citable inclusive-design or sensory features — kept short and
 * honest rather than padded, since most "sensory-friendly" claims about
 * ordinary venues can't be verified from public sources. Expand this list via
 * the Phase 0 concierge/parent-network process, not by guessing.
 */
val seedVenues: List<Venue> = listOf(
    Venue(
        id = "coles-park",
        name = "Coles Park",
        category = "Public Park",
        area = "Frazer Town",
        address = "Coles Road, Frazer Town, Bengaluru",
        tags = listOf(WHEELCHAIR_ACCESS, SENSORY_EQUIPMENT),
        description = "Bengaluru's first inclusive play space, built with BBMP and Kilikili in 2006 — ramps, accessible pathways, and wheelchair-friendly sand tables and swings.",
        sourceNote = "Public NGO/news coverage, Aug 2026 — confirm current condition before visiting.",
    ),
    Venue(
        id = "gayatri-devi-park",
        name = "Gayatri Devi Park",
        category = "Public Park",
        area = "Rajajinagar",
        address = "Gayatri Devi Park Road, Rajajinagar, Bengaluru",
        tags = listOf(WHEELCHAIR_ACCESS, SENSORY_EQUIPMENT),
        description = "Opened 2009 with wheelchair-accessible play structures, a sensory integration track, and accessible basketball poles.",
        sourceNote = "Public NGO/news coverage, Aug 2026 — confirm current condition before visiting.",
    ),
    Venue(
        id = "mnkrishnarao-park",
        name = "M. N. Krishna Rao Park",
        category = "Public Park",
        area = "Basavangudi",
        address = "Bull Temple Road, Basavangudi, Bengaluru",
        tags = listOf(WHEELCHAIR_ACCESS, SENSORY_EQUIPMENT),
        description = "Inaugurated 2010 with multi-sensory equipment — sound rods, textured tiles, wheelchair merry-go-rounds, and a wheel-through arcade.",
        sourceNote = "Public NGO/news coverage, Aug 2026 — confirm current condition before visiting.",
    ),
    Venue(
        id = "special-kids-park-cubbon",
        name = "Special Kids Park, Jawahar Bal Bhavan",
        category = "Public Park",
        area = "Cubbon Park",
        address = "Jawahar Bal Bhavan, Cubbon Park, Bengaluru",
        tags = listOf(WHEELCHAIR_ACCESS, SENSORY_EQUIPMENT),
        description = "A play area inside Cubbon Park designed for children with physical disabilities, with wheelchair-friendly swings and sand tubs.",
        sourceNote = "Public news coverage, Aug 2026 — confirm current condition before visiting.",
    ),
    Venue(
        id = "lalbagh",
        name = "Lalbagh Botanical Garden",
        category = "Park / Garden",
        area = "Mavalli",
        address = "Lalbagh Main Gate Road, Mavalli, Bengaluru 560004",
        tags = listOf(WHEELCHAIR_ACCESS, ACCESSIBLE_RESTROOM),
        description = "Large, spacious botanical garden with a dedicated disability-accessible washroom installed with RampMyCity, and wide paved paths that suit wheelchairs and prams.",
        sourceNote = "Public news coverage, Aug 2026 — confirm current condition before visiting.",
    ),
    Venue(
        id = "blr-airport-sensory-room",
        name = "Kempegowda International Airport – Sensory Room",
        category = "Travel / Airport",
        area = "Devanahalli",
        address = "Terminal 2, Level 4, near the 080 International Lounge, Kempegowda International Airport, Bengaluru",
        tags = listOf(QUIET_SPACE),
        description = "India's first airport sensory room for neurodivergent travellers — soft lighting, calming sounds, weighted blankets and a low-stimulation retreat before or after a flight.",
        sourceNote = "Airport/press coverage, Aug 2026.",
    ),
)
