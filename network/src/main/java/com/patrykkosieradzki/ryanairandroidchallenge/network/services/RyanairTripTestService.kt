package com.patrykkosieradzki.ryanairandroidchallenge.network.services

import com.patrykkosieradzki.ryanairandroidchallenge.network.model.AvailableAirportsResponse
import com.patrykkosieradzki.ryanairandroidchallenge.network.utils.ApiResult
import retrofit2.http.GET

interface RyanairTripTestService {
    @GET("static/stations.json")
    suspend fun getAvailableAirports(): ApiResult<AvailableAirportsResponse>
}
