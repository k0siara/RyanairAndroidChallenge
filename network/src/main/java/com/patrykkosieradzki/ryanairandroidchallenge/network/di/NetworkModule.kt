package com.patrykkosieradzki.ryanairandroidchallenge.network.di

import com.patrykkosieradzki.ryanairandroidchallenge.network.services.RyanairTripTestService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        CustomOkHttpClientFactory(
            appConfiguration = get()
        ).createOkHttpClient()
    } bind OkHttpClient::class

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<AppConfiguration>().baseApiUrl)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(ErrorHandlingCallAdapterFactory())
            .client(get())
            .build()
    }

    single {
        NetworkHandler(
            appConfiguration = get()
        )
    }

    single<RyanairTripTestService> {
        get<Retrofit>().create(RyanairTripTestService::class.java)
    }
//
//    single<MoviesRepository> {
//        MoviesApiRepository(
//            moviesService = get(),
//            networkHandler = get()
//        )
//    }
}