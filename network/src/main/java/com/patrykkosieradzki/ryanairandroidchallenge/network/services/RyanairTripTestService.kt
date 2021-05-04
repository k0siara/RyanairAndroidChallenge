package com.patrykkosieradzki.ryanairandroidchallenge.network.services

import com.patrykkosieradzki.ryanairandroidchallenge.network.utils.ApiResult
import retrofit2.http.GET

interface RyanairTripTestService {
    @GET("movie/now_playing")
    suspend fun getAvailableAirports(): ApiResult<MoviesPageResponse>
}
