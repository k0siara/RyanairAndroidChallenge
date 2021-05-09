package com.patrykkosieradzki.ryanairandroidchallenge.network.services

import com.patrykkosieradzki.ryanairandroidchallenge.network.model.FlightSearchResponse
import com.patrykkosieradzki.ryanairandroidchallenge.network.utils.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RyanairApiService {
    @GET("booking/v4/en-gb/Availability")
    suspend fun getSearchFlightsResults(
        @Query("dateout") dateOut: String,
        @Query("origin") originCode: String,
        @Query("destination") destinationCode: String,
        @Query("adt") adults: Int,
        @Query("teen") teens: Int,
        @Query("chd") children: Int,
        @Query("inf") inf: Int = 0,
        @Query("ToUs") toUs: String = "AGREED",
        @Query("roundtrip") roundTrip: Boolean = false
    ): ApiResult<FlightSearchResponse>
}
