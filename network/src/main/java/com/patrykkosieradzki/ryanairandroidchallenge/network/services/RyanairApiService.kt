package com.patrykkosieradzki.ryanairandroidchallenge.network.services

import com.patrykkosieradzki.ryanairandroidchallenge.network.model.FlightSearchResponse
import com.patrykkosieradzki.ryanairandroidchallenge.network.utils.ApiResult

interface RyanairApiService {
    suspend fun getSearchFlightsResults(): ApiResult<FlightSearchResponse>
}
