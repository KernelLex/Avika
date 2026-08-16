package com.avika.app.data.local

import com.avika.app.data.model.SchemeSection

/**
 * General-orientation content only, compiled Aug 2026 from official government
 * sources linked below. Rules, amounts, and processes change — always confirm
 * on the official portal before acting. This is not legal or medical advice.
 */
val seedSchemes: List<SchemeSection> = listOf(
    SchemeSection(
        id = "udid",
        title = "UDID: Getting a Disability Certificate & ID Card",
        summary = "The Unique Disability ID (UDID) is the single national ID that unlocks almost every other benefit on this page — usually the first thing to apply for.",
        details = listOf(
            "Issued by the Department of Empowerment of Persons with Disabilities (DEPwD), Government of India, for all 21 disabilities recognised under the Rights of Persons with Disabilities Act, 2016.",
            "Apply online with Aadhaar e-KYC, or at a government hospital / district disability rehabilitation centre that does UDID assessments — a medical board examines the child and certifies the disability percentage.",
            "Typical processing time is about 7–14 working days once the medical assessment is done, though it can take longer depending on the hospital's board schedule.",
            "In Bengaluru, assessments are usually done at government hospitals with a certifying Medical Board — check the official portal's facility locator for the nearest and current one, since assignments can change.",
            "Keep the UDID number handy — Niramaya, railway concession cards, and most Karnataka welfare schemes ask for it.",
        ),
        officialLink = "https://www.swavlambancard.gov.in/",
    ),
    SchemeSection(
        id = "swavlamban",
        title = "Swavlamban (Karnataka's Disability ID Card)",
        summary = "Karnataka's own name and delivery process for the disability ID card, run on top of the same central UDID system.",
        details = listOf(
            "Administered in Karnataka by the Department for the Empowerment of Differently Abled and Senior Citizens.",
            "In practice this is Karnataka's implementation of the national UDID rollout, not a separate card you apply for twice — but state-level help and camps are organised under the Swavlamban name.",
            "Karnataka has issued the card to lakhs of persons with disabilities across the state; local taluk/district offices can help if the online process gets stuck.",
        ),
        officialLink = "https://dwdsc.karnataka.gov.in/english",
    ),
    SchemeSection(
        id = "niramaya",
        title = "Niramaya Health Insurance",
        summary = "A National Trust health insurance scheme built specifically for autism, cerebral palsy, intellectual disability, and multiple disabilities — covers therapies, not just hospitalisation.",
        details = listOf(
            "Run by the National Trust (Ministry of Social Justice & Empowerment) for persons with Autism, Cerebral Palsy, Intellectual Disability, and Multiple Disabilities.",
            "Annual coverage up to ₹1,00,000 on a reimbursement basis, covering hospitalisation, corrective surgery, OPD consultations, diagnostics, medicines, and therapies including speech, occupational, and physiotherapy.",
            "A UDID number is mandatory for enrolment or renewal — get that first.",
            "Enrolment/renewal windows open on the National Trust portal each policy year — check current dates before assuming it's open.",
        ),
        officialLink = "https://nationaltrust.nic.in/niramaya/",
    ),
    SchemeSection(
        id = "railway-concession",
        title = "Railway Concessions",
        summary = "Discounted train fares for the child and one accompanying escort, now linked directly to the UDID card.",
        details = listOf(
            "Concessions range roughly 25–75% depending on travel class and disability category — for example, orthopaedic/paraplegic passengers and their escort get 75% off Second, Sleeper, First and AC 3-tier fares; visually impaired passengers and their escort get 50% off.",
            "An accompanying escort typically gets the same percentage concession as the passenger.",
            "Since the UDID integration, concession cards can be applied for online through the Indian Railways portal or at a Jan Suvidha Kendra, instead of visiting a DRM office in person.",
            "Minimum disability thresholds apply (commonly 40%) — check the current eligibility table on the official portal for the exact category.",
        ),
        officialLink = "https://www.indianrail.gov.in/enquiry/StaticPages/StaticEnquiry.jsp?StaticPage=conc_Rules.html",
    ),
    SchemeSection(
        id = "pensions",
        title = "Disability Pensions (Karnataka)",
        summary = "Monthly financial support schemes for persons with disabilities run by the Karnataka state government.",
        details = listOf(
            "Karnataka runs monthly disability pension schemes through the Department for the Empowerment of Differently Abled and Senior Citizens, alongside central schemes for severe/multiple disabilities.",
            "Eligibility, pension amount, and required documents (UDID, income certificate, residence proof) are set by the state and revised from time to time — this app deliberately doesn't quote a rupee amount since it changes; check the department's site or your nearest taluk office for the current figure.",
            "Local disability welfare/NGO offices can usually help fill the application if the online process is confusing.",
        ),
        officialLink = "https://dwdsc.karnataka.gov.in/english",
    ),
)
