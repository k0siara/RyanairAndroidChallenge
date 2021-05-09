package com.patrykkosieradzki.ryanairandroidchallenge.domain.repositories

import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.FlightSearchData
import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.FlightSearchFilters
import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.Station

interface FlightRepository {
    suspend fun getAllStations(): List<Station>
    suspend fun getFlights(flightSearchFilters: FlightSearchFilters): FlightSearchData
}
