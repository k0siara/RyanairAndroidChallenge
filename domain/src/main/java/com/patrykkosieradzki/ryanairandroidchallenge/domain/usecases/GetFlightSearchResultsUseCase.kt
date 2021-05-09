package com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases

import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.FlightSearchData
import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.FlightSearchFilters
import com.patrykkosieradzki.ryanairandroidchallenge.domain.repositories.FlightRepository

interface GetFlightSearchResultsUseCase {
    suspend fun invoke(flightSearchFilters: FlightSearchFilters): FlightSearchData
}

class GetFlightSearchResultsUseCaseImpl(
    private val flightRepository: FlightRepository
) : GetFlightSearchResultsUseCase {
    override suspend fun invoke(flightSearchFilters: FlightSearchFilters): FlightSearchData {
        return flightRepository.getFlights(flightSearchFilters)
    }
}
