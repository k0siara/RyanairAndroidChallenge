package com.patrykkosieradzki.ryanairandroidchallenge.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AvailableAirportsResponse(
    @Json(name = "stations") val stations: List<StationResponse>
)

@JsonClass(generateAdapter = true)
data class StationResponse(
    @Json(name = "code") val code: String,
    @Json(name = "name") val name: String,
    @Json(name = "alternateName") val alternateName: String?,
    @Json(name = "alias") val alias: List<String>,
    @Json(name = "countryCode") val countryCode: String,
    @Json(name = "countryName") val countryName: String,
    @Json(name = "countryAlias") val countryAlias: String?,
    @Json(name = "countryGroupCode") val countryGroupCode: String,
    @Json(name = "countryGroupName") val countryGroupName: String,
    @Json(name = "timeZoneCode") val timeZoneCode: String,
    @Json(name = "latitude") val latitude: String,
    @Json(name = "longitude") val longitude: String,
    @Json(name = "mobileBoardingPass") val mobileBoardingPass: Boolean,
    @Json(name = "markets") val markets: List<MarketResponse>,
    @Json(name = "notices") val notices: String?
)

@JsonClass(generateAdapter = true)
data class MarketResponse(
    @Json(name = "code") val code: String,
    @Json(name = "group") val group: String?
)
