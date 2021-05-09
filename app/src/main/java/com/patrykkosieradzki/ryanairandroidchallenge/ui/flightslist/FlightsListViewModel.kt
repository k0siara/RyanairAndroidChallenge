package com.patrykkosieradzki.ryanairandroidchallenge.ui.flightslist

import com.patrykkosieradzki.ryanairandroidchallenge.domain.exceptions.ApiException
import com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases.GetFlightSearchResultsUseCase
import com.patrykkosieradzki.ryanairandroidchallenge.ui.FlightSearchFiltersParcel
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseViewModel
import com.patrykkosieradzki.ryanairandroidchallenge.utils.ErrorEvent
import com.patrykkosieradzki.ryanairandroidchallenge.utils.ViewState

class FlightsListViewModel(
    private val getFlightSearchResultsUseCase: GetFlightSearchResultsUseCase
) : BaseViewModel<FlightsListViewState>(
    initialState = FlightsListViewState(inProgress = true)
) {

    fun searchForFlights(flightSearchFilters: FlightSearchFiltersParcel) {
        safeLaunch {
            val flightSearchData = getFlightSearchResultsUseCase.invoke(flightSearchFilters)
            updateViewState {
                it.copy(
                    dateOut = flightSearchFilters.dateOut,
                    originCode = flightSearchFilters.originCode,
                    destinationCode = flightSearchFilters.destinationCode,
                    inProgress = false
                )
            }
        }
    }

    override fun updateError(exception: Throwable): ErrorEvent {
        if (exception is ApiException.UnknownApiException) {
            if (exception.errorMessage.contains(NO_HTTP_RESOURCE_FOUND, ignoreCase = true)) {
                updateViewState { it.copy(inProgress = false) }
                return ErrorEvent(ApiException.OtherError(NO_FLIGHTS_ERROR_MESSAGE))
            }
        }
        return super.updateError(exception)
    }

    companion object {
        const val NO_HTTP_RESOURCE_FOUND = "No HTTP resource was found"
        const val NO_FLIGHTS_ERROR_MESSAGE = "Sorry, we were not able to find any flights"
    }
}

data class FlightsListViewState(
    val dateOut: String = "",
    val originCode: String = "",
    val destinationCode: String = "",
    override val inProgress: Boolean,
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
