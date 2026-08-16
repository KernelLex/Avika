package com.avika.app.data.local

import com.avika.app.data.model.Clinic
import com.avika.app.data.model.Specialty
import com.avika.app.data.model.Specialty.ABA_THERAPY
import com.avika.app.data.model.Specialty.DEVELOPMENTAL_PEDIATRICS
import com.avika.app.data.model.Specialty.OCCUPATIONAL_THERAPY
import com.avika.app.data.model.Specialty.SPEECH_THERAPY

/**
 * Compiled from public clinic websites/listings, Aug 2026. Not independently
 * field-verified — confirm address/phone/hours before relying on an entry.
 * Replace with the Phase 0 concierge dataset once it exists.
 */
val seedClinics: List<Clinic> = listOf(
    Clinic(
        id = "pinnacle-jayanagar",
        name = "Pinnacle Blooms Network – Jayanagar",
        specialties = listOf(SPEECH_THERAPY, OCCUPATIONAL_THERAPY, ABA_THERAPY),
        area = "Jayanagar",
        address = "12th Main, 27th Cross, 4th Block East, Jayanagar, Bengaluru 560011",
        phone = "+919100181181",
    ),
    Clinic(
        id = "pinnacle-indiranagar",
        name = "Pinnacle Blooms Network – Indiranagar",
        specialties = listOf(SPEECH_THERAPY, OCCUPATIONAL_THERAPY, ABA_THERAPY),
        area = "Indiranagar",
        address = "Indiranagar, Bengaluru",
        phone = "+919100181181",
        notes = "Exact street address not published online — confirm unit/floor by phone.",
    ),
    Clinic(
        id = "eav-sahakarnagar",
        name = "Early Autism Ventures – Sahakara Nagar",
        specialties = listOf(ABA_THERAPY, OCCUPATIONAL_THERAPY),
        area = "Sahakara Nagar",
        address = "2153, D Block, 16th Cross, 8th Main, Sahakara Nagar, Bengaluru",
        phone = "+918929153820",
    ),
    Clinic(
        id = "eav-jpnagar",
        name = "Early Autism Ventures – J P Nagar",
        specialties = listOf(ABA_THERAPY, OCCUPATIONAL_THERAPY),
        area = "J P Nagar",
        address = "No. 681, 6th C Main Road, 14th Cross, J P Nagar 3rd Phase, Bengaluru",
        phone = "+918929153820",
    ),
    Clinic(
        id = "eav-hsr",
        name = "Early Autism Ventures – HSR Layout",
        specialties = listOf(ABA_THERAPY, OCCUPATIONAL_THERAPY, SPEECH_THERAPY),
        area = "HSR Layout",
        address = "Venkatadri, 9 & 10, 4th Cross Rd, ITI Layout, Hosapalya, Muneshwara Nagar, Bengaluru 560068",
        phone = "+918929153820",
    ),
    Clinic(
        id = "eav-kalyannagar",
        name = "Early Autism Ventures – Kalyan Nagar",
        specialties = listOf(ABA_THERAPY, OCCUPATIONAL_THERAPY),
        area = "Kalyan Nagar",
        address = "931, 5th Cross, 9th Main Rd, HRBR Layout 1st Block, Kalyan Nagar, Bengaluru 560043",
        phone = "+918929153820",
    ),
    Clinic(
        id = "steppingstones-marathahalli",
        name = "Stepping Stones Center",
        specialties = listOf(ABA_THERAPY, SPEECH_THERAPY, OCCUPATIONAL_THERAPY),
        area = "Marathahalli",
        address = "76, Karthik Nagar, Marathahalli, Bengaluru 560037",
        phone = "+917022626487",
        notes = "Phone listed is a WhatsApp contact number.",
    ),
    Clinic(
        id = "sambhavam-whitefield",
        name = "Sambhavam Center for Autism",
        specialties = listOf(ABA_THERAPY),
        area = "Whitefield",
        address = "Orange International Preschool & Day Care, 563, 60 Feet Road, AECS Layout C Block, Brookfield, Bengaluru",
        phone = "+917760764217",
        notes = "Phone listed is a WhatsApp contact number.",
    ),
    Clinic(
        id = "pragyan-basavangudi",
        name = "Pragyan Child Development Centre – Basavangudi",
        specialties = listOf(SPEECH_THERAPY, OCCUPATIONAL_THERAPY, ABA_THERAPY),
        area = "Basavangudi",
        address = "Kapini Ganga Building, #39/2, 4th Floor, Sannidhi Road, next to NR Colony Bus Stop, Bengaluru 560004",
        phone = "+917016635914",
    ),
    Clinic(
        id = "pragyan-rajajinagar",
        name = "Pragyan Child Development Centre – Rajajinagar",
        specialties = listOf(SPEECH_THERAPY, OCCUPATIONAL_THERAPY, ABA_THERAPY),
        area = "Rajajinagar",
        address = "No. 415, 20th Main Road, 1st Block, West of Chord Road, Nagapura, Bengaluru 560010",
        phone = "+917016635914",
    ),
    Clinic(
        id = "pragyan-rtnagar",
        name = "Pragyan Child Development Centre – RT Nagar",
        specialties = listOf(SPEECH_THERAPY, OCCUPATIONAL_THERAPY, ABA_THERAPY),
        area = "RT Nagar",
        address = "No. 3, 1st Floor, 10th Cross, CBI Road, Ganganagar, Bengaluru 560032",
        phone = "+917016635914",
    ),
    Clinic(
        id = "pragyan-varthur",
        name = "Pragyan Child Development Centre – Varthur",
        specialties = listOf(SPEECH_THERAPY, OCCUPATIONAL_THERAPY, ABA_THERAPY),
        area = "Varthur",
        address = "3rd Floor, Khata No. 1561, Balagere Road, Devasthanagalu, Bengaluru 560087",
        phone = "+917016635914",
    ),
    Clinic(
        id = "ssot-uttarahalli",
        name = "SS Occupational Therapy – Centre for Child Development",
        specialties = listOf(OCCUPATIONAL_THERAPY),
        area = "Uttarahalli",
        address = "Uttarahalli, Bengaluru",
        phone = "+918760626262",
        notes = "Exact street address not published online — confirm by phone.",
    ),
    Clinic(
        id = "ssot-rrnagar",
        name = "SS Occupational Therapy – Centre for Child Development",
        specialties = listOf(OCCUPATIONAL_THERAPY),
        area = "Rajarajeshwari Nagar",
        address = "Rajarajeshwari Nagar, Bengaluru",
        phone = "+918760626262",
        notes = "Exact street address not published online — confirm by phone.",
    ),
    Clinic(
        id = "manipal-autism-clinic",
        name = "Manipal Hospital – Autism Clinic",
        specialties = listOf(DEVELOPMENTAL_PEDIATRICS, OCCUPATIONAL_THERAPY, SPEECH_THERAPY, ABA_THERAPY),
        area = "Old Airport Road",
        address = "Manipal Hospital, 98 HAL Old Airport Road, Kodihalli, Bengaluru 560017",
        phone = "+918025023225",
        notes = "Autism Clinic hours: 3–6 PM, Monday–Saturday.",
    ),
    Clinic(
        id = "kindersurge-koramangala",
        name = "KinderSurge",
        specialties = listOf(DEVELOPMENTAL_PEDIATRICS),
        area = "Koramangala",
        address = "522, 3rd Cross Rd, KHB Block, Koramangala, Bengaluru",
        phone = "+919880271583",
    ),
    Clinic(
        id = "rainbow-marathahalli",
        name = "Rainbow Children's Hospital – Marathahalli",
        specialties = listOf(DEVELOPMENTAL_PEDIATRICS),
        area = "Marathahalli",
        address = "Marathahalli, Bengaluru",
        phone = "18002122",
        notes = "Developmental pediatrics consult (Dr. Rachana G) — confirm exact address/timing by phone.",
    ),
)
