package com.avika.app.data.model

data class Family(
    val id: String = "",
    val name: String = "",
    val inviteCode: String = "",
    val memberUids: List<String> = emptyList(),
)

data class FamilyMember(
    val uid: String = "",
    val displayName: String = "",
    val relationship: String = "", // e.g. "Parent", "Sibling"
)

data class ChildProfile(
    val id: String = "",
    val name: String = "",
    val dateOfBirth: String = "", // ISO-8601 date, e.g. 2019-04-12
)
