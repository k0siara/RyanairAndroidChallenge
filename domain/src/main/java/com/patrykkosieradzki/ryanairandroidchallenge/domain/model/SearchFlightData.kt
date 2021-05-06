package com.patrykkosieradzki.ryanairandroidchallenge.domain.model

data class FlightSearchData(
    val termsOfUse: String,
    val currency: String,
    val currPrecision: Long,
    val routeGroup: String,
    val tripType: String,
    val upgradeType: String,
    val trips: List<Trip>,
    val serverTimeUTC: String
)

data class Trip(
    val origin: String,
    val originName: String,
    val destination: String,
    val destinationName: String,
    val routeGroup: String,
    val tripType: String,
    val upgradeType: String,
    val dates: List<FlightDate>
)

data class FlightDate(
    val dateOut: String,
    val flights: List<Flight>
)

data class Flight(
    val faresLeft: Long,
    val flightKey: String,
    val infantsLeft: Long,
    val regularFare: RegularFare,
    val operatedBy: String,
    val segments: List<Segment>,
    val flightNumber: String,
    val time: List<String>,
    val timeUTC: List<String>,
    val duration: String
)

data class RegularFare(
    val fareKey: String,
    val fareClass: String,
    val fares: List<Fare>
)

data class Fare(
    val type: String,
    val amount: Long,
    val count: Long,
    val hasDiscount: Boolean,
    val publishedFare: Long,
    val discountInPercent: Long,
    val hasPromoDiscount: Boolean,
    val discountAmount: Long,
    val hasBogof: Boolean
)

data class Segment(
    val segmentNr: Long,
    val origin: String,
    val destination: String,
    val flightNumber: String,
    val time: List<String>,
    val timeUTC: List<String>,
    val duration: String
)
