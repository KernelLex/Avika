package com.avika.app.data.repository

import com.avika.app.data.local.seedClinics
import com.avika.app.data.model.Clinic

interface ClinicRepository {
    fun getAll(): List<Clinic>
    fun getById(id: String): Clinic?
}

/**
 * Reads from bundled sample data. Swap the body of [getAll] for a Firestore
 * query when the backend is ready — callers only depend on this interface.
 */
class LocalClinicRepository : ClinicRepository {
    override fun getAll(): List<Clinic> = seedClinics
    override fun getById(id: String): Clinic? = seedClinics.find { it.id == id }
}
