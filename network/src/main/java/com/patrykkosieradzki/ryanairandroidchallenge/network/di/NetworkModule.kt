package com.patrykkosieradzki.ryanairandroidchallenge.network.di

import com.patrykkosieradzki.ryanairandroidchallenge.domain.AppConfiguration
import com.patrykkosieradzki.ryanairandroidchallenge.domain.repositories.FlightRepository
import com.patrykkosieradzki.ryanairandroidchallenge.network.repositories.FlightApiRepository
import com.patrykkosieradzki.ryanairandroidchallenge.network.services.RyanairApiService
import com.patrykkosieradzki.ryanairandroidchallenge.network.services.RyanairTripTestService
import com.patrykkosieradzki.ryanairandroidchallenge.network.utils.CustomOkHttpClientFactory
import com.patrykkosieradzki.ryanairandroidchallenge.network.utils.ErrorHandlingCallAdapterFactory
import com.patrykkosieradzki.ryanairandroidchallenge.network.utils.NetworkHandler
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val TRIP_TEST_REST_API = "TRIP_TEST_REST_API"
const val RYANAIR_REST_API = "RYANAIR_REST_API"

val networkModule = module {

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        CustomOkHttpClientFactory(
            appConfiguration = get(),
        ).createOkHttpClient()
    } bind OkHttpClient::class

    single<Retrofit>(named(TRIP_TEST_REST_API)) {
        Retrofit.Builder()
            .baseUrl(get<AppConfiguration>().tripTestApiUrl)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(ErrorHandlingCallAdapterFactory())
            .client(get())
            .build()
    }

    single<Retrofit>(named(RYANAIR_REST_API)) {
        Retrofit.Builder()
            .baseUrl(get<AppConfiguration>().ryanairApiUrl)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(ErrorHandlingCallAdapterFactory())
            .client(get())
            .build()
    }

    single<RyanairTripTestService> {
        get<Retrofit>(named(TRIP_TEST_REST_API)).create(RyanairTripTestService::class.java)
    }

    single<RyanairApiService> {
        get<Retrofit>(named(RYANAIR_REST_API)).create(RyanairApiService::class.java)
    }

    single {
        NetworkHandler(
            appConfiguration = get(),
        )
    }

    single<FlightRepository> {
        FlightApiRepository(
            tripTestService = get(),
            apiService = get(),
            networkHandler = get(),
        )
    }
}
