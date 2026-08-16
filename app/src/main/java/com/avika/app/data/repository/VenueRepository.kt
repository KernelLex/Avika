package com.avika.app.data.repository

import com.avika.app.data.local.seedVenues
import com.avika.app.data.model.Venue

interface VenueRepository {
    fun getAll(): List<Venue>
    fun getById(id: String): Venue?
}

/**
 * Reads from bundled sample data. Swap the body of [getAll] for a Firestore
 * query when the backend is ready — callers only depend on this interface.
 */
class LocalVenueRepository : VenueRepository {
    override fun getAll(): List<Venue> = seedVenues
    override fun getById(id: String): Venue? = seedVenues.find { it.id == id }
}
