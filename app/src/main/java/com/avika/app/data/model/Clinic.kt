package com.avika.app.data.model

enum class Specialty(val label: String) {
    OCCUPATIONAL_THERAPY("Occupational Therapy"),
    SPEECH_THERAPY("Speech Therapy"),
    ABA_THERAPY("ABA / Autism Therapy"),
    DEVELOPMENTAL_PEDIATRICS("Developmental Pediatrics"),
}

data class Clinic(
    val id: String,
    val name: String,
    val specialties: List<Specialty>,
    val area: String,
    val address: String,
    val phone: String?,
    val notes: String? = null,
)
