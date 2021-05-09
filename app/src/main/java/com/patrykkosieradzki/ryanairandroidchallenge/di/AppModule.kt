package com.patrykkosieradzki.ryanairandroidchallenge.di

import com.patrykkosieradzki.ryanairandroidchallenge.RyanairAppConfiguration
import com.patrykkosieradzki.ryanairandroidchallenge.domain.AppConfiguration
import com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases.GetAllStationsUseCase
import com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases.GetAllStationsUseCaseImpl
import com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases.GetFlightSearchResultsUseCase
import com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases.GetFlightSearchResultsUseCaseImpl
import com.patrykkosieradzki.ryanairandroidchallenge.ui.search.FlightSearchViewModel
import com.patrykkosieradzki.ryanairandroidchallenge.ui.flights.FlightsListViewModel
import com.patrykkosieradzki.ryanairandroidchallenge.ui.stations.SelectStationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<AppConfiguration> {
        RyanairAppConfiguration()
    }

    factory<GetAllStationsUseCase> {
        GetAllStationsUseCaseImpl(
            flightRepository = get()
        )
    }

    factory<GetFlightSearchResultsUseCase> {
        GetFlightSearchResultsUseCaseImpl(
            flightRepository = get()
        )
    }

    viewModel {
        FlightSearchViewModel()
    }

    viewModel {
        SelectStationViewModel(
            getAllStationsUseCase = get()
        )
    }

    viewModel {
        FlightsListViewModel(
            getFlightSearchResultsUseCase = get()
        )
    }
}
