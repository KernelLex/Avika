package com.avika.app.data.model

data class SchemeSection(
    val id: String,
    val title: String,
    val summary: String,
    val details: List<String>,
    val officialLink: String?,
)
