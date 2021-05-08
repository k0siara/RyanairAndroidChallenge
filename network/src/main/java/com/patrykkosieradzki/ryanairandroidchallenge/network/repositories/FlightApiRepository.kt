package com.patrykkosieradzki.ryanairandroidchallenge.network.repositories

import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.*
import com.patrykkosieradzki.ryanairandroidchallenge.domain.repositories.FlightRepository
import com.patrykkosieradzki.ryanairandroidchallenge.network.model.*
import com.patrykkosieradzki.ryanairandroidchallenge.network.services.RyanairApiService
import com.patrykkosieradzki.ryanairandroidchallenge.network.services.RyanairTripTestService
import com.patrykkosieradzki.ryanairandroidchallenge.network.utils.NetworkHandler

class FlightApiRepository(
    private val tripTestService: RyanairTripTestService,
    private val apiService: RyanairApiService,
    private val networkHandler: NetworkHandler,
) : FlightRepository {
    override suspend fun getAllStations(): List<Station> {
        return networkHandler.safeNetworkCall {
            tripTestService.getAvailableAirports()
        }.stations.toDomain()
    }

    override suspend fun getFlights(
        dateOut: String,
        originCode: String,
        destinationCode: String,
        adults: Int,
        teens: Int,
        children: Int
    ): FlightSearchData {
        return networkHandler.safeNetworkCall {
            apiService.getSearchFlightsResults(
                dateOut,
                originCode,
                destinationCode,
                adults,
                teens,
                children
            )
        }.toDomain()
    }
}

fun List<StationResponse>.toDomain() = map { it.toDomain() }

fun StationResponse.toDomain() = Station(
    code = code,
    name = name,
    alternateName = alternateName,
    alias = alias,
    countryCode = countryCode,
    countryName = countryName,
    countryAlias = countryAlias,
    countryGroupCode = countryGroupCode,
    countryGroupName = countryGroupName,
    timeZoneCode = timeZoneCode,
    latitude = latitude,
    longitude = longitude,
    mobileBoardingPass = mobileBoardingPass,
    markets = markets.map { it.toDomain() },
    notices = notices,
)

fun MarketResponse.toDomain() = Market(
    code = code,
    group = group,
)

fun FlightSearchResponse.toDomain() = FlightSearchData(
    termsOfUse = termsOfUse,
    currency = currency,
    currPrecision = currPrecision,
    routeGroup = routeGroup,
    tripType = tripType,
    upgradeType = upgradeType,
    trips = trips.map { it.toDomain() },
    serverTimeUTC = serverTimeUTC
)

fun TripResponse.toDomain() = Trip(
    origin = origin,
    originName = originName,
    destination = destination,
    destinationName = destinationName,
    routeGroup = routeGroup,
    tripType = tripType,
    upgradeType = upgradeType,
    dates = dates.map { it.toDomain() }
)

fun FlightDateResponse.toDomain() = FlightDate(
    dateOut = dateOut,
    flights = flights.map { it.toDomain() }
)

fun FlightResponse.toDomain() = Flight(
    faresLeft = faresLeft,
    flightKey = flightKey,
    infantsLeft = infantsLeft,
    regularFare = RegularFare(
        fareClass = regularFare.fareClass,
        fareKey = regularFare.fareKey,
        fares = regularFare.fares.map { it.toDomain() }
    ),
    operatedBy = operatedBy,
    segments = segments.map { it.toDomain() },
    flightNumber = flightNumber,
    time = time,
    timeUTC = timeUTC,
    duration = duration
)

fun SegmentResponse.toDomain() = Segment(
    segmentNr = segmentNr,
    origin = origin,
    destination = destination,
    flightNumber = flightNumber,
    time = time,
    timeUTC = timeUTC,
    duration = duration
)

fun FareResponse.toDomain() = Fare(
    type = type,
    amount = amount,
    count = count,
    hasDiscount = hasDiscount,
    publishedFare = publishedFare,
    discountInPercent = discountInPercent,
    hasPromoDiscount = hasPromoDiscount,
    discountAmount = discountAmount,
    hasBogof = hasBogof
)
