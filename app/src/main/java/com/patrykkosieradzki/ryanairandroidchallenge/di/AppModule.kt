package com.patrykkosieradzki.ryanairandroidchallenge.di

import com.patrykkosieradzki.ryanairandroidchallenge.RyanairAppConfiguration
import com.patrykkosieradzki.ryanairandroidchallenge.domain.AppConfiguration
import com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases.GetAllStationsUseCase
import com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases.GetAllStationsUseCaseImpl
import com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch.FlightSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<AppConfiguration> {
        RyanairAppConfiguration()
    }

    factory<GetAllStationsUseCase> {
        GetAllStationsUseCaseImpl(
            flightRepository = get(),
        )
    }

    viewModel {
        FlightSearchViewModel(
            getAllStationsUseCase = get(),
        )
    }
}
