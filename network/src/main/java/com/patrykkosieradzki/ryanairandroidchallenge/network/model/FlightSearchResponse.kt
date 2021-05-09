package com.patrykkosieradzki.ryanairandroidchallenge.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlightSearchResponse(
    @Json(name = "termsOfUse") val termsOfUse: String,
    @Json(name = "currency") val currency: String,
    @Json(name = "currPrecision") val currPrecision: Long,
    @Json(name = "routeGroup") val routeGroup: String,
    @Json(name = "tripType") val tripType: String,
    @Json(name = "upgradeType") val upgradeType: String,
    @Json(name = "trips") val trips: List<TripResponse>,
    @Json(name = "serverTimeUTC") val serverTimeUTC: String
)

@JsonClass(generateAdapter = true)
data class TripResponse(
    @Json(name = "origin") val origin: String,
    @Json(name = "originName") val originName: String,
    @Json(name = "destination") val destination: String,
    @Json(name = "destinationName") val destinationName: String,
    @Json(name = "routeGroup") val routeGroup: String,
    @Json(name = "tripType") val tripType: String,
    @Json(name = "upgradeType") val upgradeType: String,
    @Json(name = "dates") val dates: List<FlightDateResponse>
)

@JsonClass(generateAdapter = true)
data class FlightDateResponse(
    @Json(name = "dateOut") val dateOut: String,
    @Json(name = "flights") val flights: List<FlightResponse>
)

@JsonClass(generateAdapter = true)
data class FlightResponse(
    @Json(name = "faresLeft") val faresLeft: Long,
    @Json(name = "flightKey") val flightKey: String,
    @Json(name = "infantsLeft") val infantsLeft: Long,
    @Json(name = "regularFare") val regularFare: RegularFareResponse,
    @Json(name = "operatedBy") val operatedBy: String,
    @Json(name = "segments") val segments: List<SegmentResponse>,
    @Json(name = "flightNumber") val flightNumber: String,
    @Json(name = "time") val time: List<String>,
    @Json(name = "timeUTC") val timeUTC: List<String>,
    @Json(name = "duration") val duration: String
)

@JsonClass(generateAdapter = true)
data class RegularFareResponse(
    @Json(name = "fareKey") val fareKey: String,
    @Json(name = "fareClass") val fareClass: String,
    @Json(name = "fares") val fares: List<FareResponse>
)

@JsonClass(generateAdapter = true)
data class FareResponse(
    @Json(name = "type") val type: String,
    @Json(name = "amount") val amount: Float,
    @Json(name = "count") val count: Long,
    @Json(name = "hasDiscount") val hasDiscount: Boolean,
    @Json(name = "publishedFare") val publishedFare: Float,
    @Json(name = "discountInPercent") val discountInPercent: Float,
    @Json(name = "hasPromoDiscount") val hasPromoDiscount: Boolean,
    @Json(name = "discountAmount") val discountAmount: Float,
    @Json(name = "hasBogof") val hasBogof: Boolean
)

@JsonClass(generateAdapter = true)
data class SegmentResponse(
    @Json(name = "segmentNr") val segmentNr: Long,
    @Json(name = "origin") val origin: String,
    @Json(name = "destination") val destination: String,
    @Json(name = "flightNumber") val flightNumber: String,
    @Json(name = "time") val time: List<String>,
    @Json(name = "timeUTC") val timeUTC: List<String>,
    @Json(name = "duration") val duration: String
)
