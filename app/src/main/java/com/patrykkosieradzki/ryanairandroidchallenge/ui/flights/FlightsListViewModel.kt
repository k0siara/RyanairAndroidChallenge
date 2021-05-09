package com.patrykkosieradzki.ryanairandroidchallenge.ui.flights

import com.patrykkosieradzki.ryanairandroidchallenge.domain.exceptions.ApiException
import com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases.GetFlightSearchResultsUseCase
import com.patrykkosieradzki.ryanairandroidchallenge.ui.FlightSearchFiltersParcel
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseViewModel
import com.patrykkosieradzki.ryanairandroidchallenge.utils.ErrorEvent
import com.patrykkosieradzki.ryanairandroidchallenge.utils.ViewState
import org.threeten.bp.LocalDateTime

class FlightsListViewModel(
    private val getFlightSearchResultsUseCase: GetFlightSearchResultsUseCase
) : BaseViewModel<FlightsListViewState>(
    initialState = FlightsListViewState(inProgress = true)
) {

    fun searchForFlights(flightSearchFilters: FlightSearchFiltersParcel) {
        safeLaunch {
            val flightSearchData = getFlightSearchResultsUseCase.invoke(flightSearchFilters)

            val flights = flightSearchData.trips.map { trip ->
                trip.dates.map { tripDate ->
                    tripDate.flights.map { flight ->
                        println("dupa")
                        FlightListItem(
                            originCode = trip.origin,
                            originName = trip.originName,
                            destinationCode = trip.destination,
                            destinationName = trip.destinationName,
                            duration = flight.duration,
                            amount = flight.regularFare.fares[0].amount,
                            currency = flightSearchData.currency,
                            flightNumber = flight.segments[0].flightNumber,
                            startDateTime = LocalDateTime.parse(flight.segments[0].time[0]),
                            endDateTime = LocalDateTime.parse(flight.segments[0].time[1])
                        )
                    }
                }.flatten()
            }.flatten()

            println(flights.size)

            updateViewState {
                it.copy(
                    dateOut = flightSearchFilters.dateOut,
                    originCode = flightSearchFilters.originCode,
                    destinationCode = flightSearchFilters.destinationCode,
                    flights = flights,
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
    val flights: List<FlightListItem> = emptyList(),
    override val inProgress: Boolean,
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
