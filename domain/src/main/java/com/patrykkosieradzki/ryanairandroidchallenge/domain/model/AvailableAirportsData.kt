package com.patrykkosieradzki.ryanairandroidchallenge.domain.model

data class AvailableAirportsData(
    val stations: List<Station>
)

data class Station(
    val code: String,
    val name: String,
    val alternateName: String,
    val alias: List<String>,
    val countryCode: String,
    val countryName: String,
    val countryAlias: String? = null,
    val countryGroupCode: String,
    val countryGroupName: String,
    val timeZoneCode: String,
    val latitude: String,
    val longitude: String,
    val mobileBoardingPass: Boolean,
    val markets: List<Market>,
    val notices: String? = null
)

data class Market(
    val code: String,
    val group: String? = null
)
