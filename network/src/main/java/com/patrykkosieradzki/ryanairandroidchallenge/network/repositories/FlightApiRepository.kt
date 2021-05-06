package com.patrykkosieradzki.ryanairandroidchallenge.network.repositories

import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.FlightSearchData
import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.Market
import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.Station
import com.patrykkosieradzki.ryanairandroidchallenge.domain.repositories.FlightRepository
import com.patrykkosieradzki.ryanairandroidchallenge.network.model.MarketResponse
import com.patrykkosieradzki.ryanairandroidchallenge.network.model.StationResponse
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

    override suspend fun getFlights(): List<FlightSearchData> {
        TODO("Not yet implemented")
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
