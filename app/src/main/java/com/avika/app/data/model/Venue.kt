package com.avika.app.data.model

enum class VenueTag(val label: String) {
    WHEELCHAIR_ACCESS("Wheelchair access"),
    SENSORY_EQUIPMENT("Sensory / inclusive play equipment"),
    QUIET_SPACE("Dedicated quiet space"),
    ACCESSIBLE_RESTROOM("Accessible restroom"),
}

data class Venue(
    val id: String,
    val name: String,
    val category: String,
    val area: String,
    val address: String,
    val tags: List<VenueTag>,
    val description: String,
    val sourceNote: String,
)
