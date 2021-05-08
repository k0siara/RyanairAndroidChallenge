package com.patrykkosieradzki.ryanairandroidchallenge.domain.repositories

import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.FlightSearchData
import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.Station

interface FlightRepository {
    suspend fun getAllStations(): List<Station>
    suspend fun getFlights(
        dateOut: String,
        originCode: String,
        destinationCode: String,
        adults: Int,
        teens: Int,
        children: Int
    ): FlightSearchData
}
