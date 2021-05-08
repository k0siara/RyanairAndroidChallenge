package com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch

import com.hadilq.liveevent.LiveEvent
import com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch.FlightSearchViewModel.Companion.DATE_FORMATTER
import com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch.FlightSearchViewModel.Companion.MAX_SOLD_FLIGHTS
import com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch.FlightSearchViewModel.Companion.MEAN_SOLD_FLIGHTS
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseViewModel
import com.patrykkosieradzki.ryanairandroidchallenge.utils.ViewState
import com.patrykkosieradzki.ryanairandroidchallenge.utils.extensions.fireEvent
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class FlightSearchViewModel : BaseViewModel<FlightSearchViewState>(
    initialState = FlightSearchViewState(inProgress = false)
) {
    val departureDateChangeEvent = LiveEvent<Unit>()
    val chooseDepartureStationEvent = LiveEvent<Unit>()
    val chooseArrivalStationEvent = LiveEvent<Unit>()

    fun onDepartureDateClicked() {
        departureDateChangeEvent.fireEvent()
    }

    fun updateAdultsSelected(adults: Int) {
        updateViewState {
            it.copy(
                adultsSelected = adults
            )
        }
    }

    fun updateTeensSelected(teens: Int) {
        updateViewState {
            it.copy(
                teensSelected = teens
            )
        }
    }

    fun updateChildrenSelected(children: Int) {
        updateViewState {
            it.copy(
                childrenSelected = children
            )
        }
    }

    fun updateDepartureDate(date: LocalDate) {
        updateViewState {
            it.copy(
                departureDate = date
            )
        }
    }

    fun updateMinAndMaxPriceValue(min: Int, max: Int) {
        updateViewState {
            it.copy(
                selectedMinPrice = min,
                selectedMaxPrice = max
            )
        }
    }

    fun onDepartureFieldClicked() {
        chooseDepartureStationEvent.fireEvent()
    }

    fun onArrivalFieldClicked() {
        chooseArrivalStationEvent.fireEvent()
    }

    fun updateDepartureStation(name: String, code: String) {
        updateViewState {
            it.copy(
                selectedDepartureStationName = name,
                selectedDepartureStationCode = code
            )
        }
    }

    fun updateArrivalStation(name: String, code: String) {
        updateViewState {
            it.copy(
                selectedArrivalStationName = name,
                selectedArrivalStationCode = code
            )
        }
    }

    fun onSearchButtonClicked() {
    }

    companion object {
        val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        const val MAX_SOLD_FLIGHTS = 1000
        const val MEAN_SOLD_FLIGHTS = 150
    }
}

data class FlightSearchViewState(
    override val inProgress: Boolean,
    val selectedDepartureStationName: String = "",
    val selectedDepartureStationCode: String = "",
    val selectedArrivalStationName: String = "",
    val selectedArrivalStationCode: String = "",
    val adultsSelected: Int = 0,
    val teensSelected: Int = 0,
    val childrenSelected: Int = 0,
    val departureDate: LocalDate = LocalDate.now(),
    val maxFlightSoldPrice: Int = MAX_SOLD_FLIGHTS,
    val meanFlightSoldPrice: Int = MEAN_SOLD_FLIGHTS,
    val selectedMinPrice: Int = 0,
    val selectedMaxPrice: Int = MEAN_SOLD_FLIGHTS
) : ViewState {
    val departureDateStr: String = departureDate.format(DATE_FORMATTER)
    val selectedMinPriceStr: String = selectedMinPrice.toString()
    val selectedMaxPriceStr: String = selectedMaxPrice.toString()

    override fun toSuccess() = copy(inProgress = false)
}
