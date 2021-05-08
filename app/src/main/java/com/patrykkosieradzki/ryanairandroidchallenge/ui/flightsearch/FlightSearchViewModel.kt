package com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch

import com.hadilq.liveevent.LiveEvent
import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.Station
import com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases.GetAllStationsUseCase
import com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch.FlightSearchViewModel.Companion.DATE_FORMATTER
import com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch.FlightSearchViewModel.Companion.MAX_SOLD_FLIGHTS
import com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch.FlightSearchViewModel.Companion.MEAN_SOLD_FLIGHTS
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseViewModel
import com.patrykkosieradzki.ryanairandroidchallenge.utils.ViewState
import com.patrykkosieradzki.ryanairandroidchallenge.utils.extensions.fireEvent
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class FlightSearchViewModel(
    private val getAllStationsUseCase: GetAllStationsUseCase
) : BaseViewModel<FlightSearchViewState>(
    initialState = FlightSearchViewState(inProgress = true)
) {
    val departureDateChangeEvent = LiveEvent<Unit>()

    override fun initialize() {
        super.initialize()
        safeLaunch {
            val stations = getAllStationsUseCase.invoke()
            updateViewState {
                it.copy(
                    availableStations = convertStationsToGroupedStationList(stations),
                    inProgress = false,
                )
            }
        }
    }

    private fun convertStationsToGroupedStationList(stations: List<Station>): List<StationListItem> {
        return stations.groupBy { it.countryName }.entries
            .map {
                ArrayList<StationListItem>().apply {
                    add(StationHeaderItem(it.key))
                    it.value.forEach { station -> add(StationGroupItem(station.name)) }
                }
            }.flatten()
    }

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

    fun onSearchButtonClicked() {
    }

    companion object {
        val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        const val MAX_SOLD_FLIGHTS = 1000
        const val MEAN_SOLD_FLIGHTS = 150
    }
}

data class FlightSearchViewState(
    override val inProgress: Boolean,
    val availableStations: List<StationListItem> = emptyList(),
    val selectedDepartureStation: String = "",
    val selectedArrivalStation: String = "",
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
