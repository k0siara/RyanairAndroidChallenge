package com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases

import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.Station
import com.patrykkosieradzki.ryanairandroidchallenge.domain.repositories.FlightRepository

interface GetAllStationsUseCase {
    suspend fun invoke(): List<Station>
}

class GetAllStationsUseCaseImpl(
    private val flightRepository: FlightRepository
) : GetAllStationsUseCase {
    override suspend fun invoke() = flightRepository.getAllStations()
}
