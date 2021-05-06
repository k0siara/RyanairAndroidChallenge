package com.patrykkosieradzki.ryanairandroidchallenge.di

import com.patrykkosieradzki.ryanairandroidchallenge.RyanairAppConfiguration
import com.patrykkosieradzki.ryanairandroidchallenge.domain.AppConfiguration
import org.koin.dsl.module

val appModule = module {

    single<AppConfiguration> {
        RyanairAppConfiguration()
    }

//    factory<GetNowPlayingMoviesUseCase> {
//        GetNowPlayingMoviesUseCaseImpl(
//            moviesRepository = get()
//        )
//    }
//
//    factory<GetPopularMoviesUseCase> {
//        GetPopularMoviesUseCaseImpl(
//            moviesRepository = get()
//        )
//    }
//
//    factory<GetMovieDetailsUseCase> {
//        GetMovieDetailsUseCaseImpl(
//            moviesRepository = get()
//        )
//    }
//
//    viewModel {
//        MovieListViewModel(
//            getNowPlayingMoviesUse = get(),
//            getPopularMoviesUseCase = get(),
//            appConfiguration = get()
//        )
//    }
//
//    viewModel {
//        MovieDetailsViewModel(
//            getMovieDetailsUseCase = get(),
//            appConfiguration = get()
//        )
//    }
}
