package com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch

import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.Station
import com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases.GetAllStationsUseCase
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseViewModel
import com.patrykkosieradzki.ryanairandroidchallenge.utils.ViewState

class FlightSearchViewModel(
    private val getAllStationsUseCase: GetAllStationsUseCase
) : BaseViewModel<FlightSearchViewState>(
    initialState = FlightSearchViewState(inProgress = true)
) {
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
}

data class FlightSearchViewState(
    override val inProgress: Boolean,
    val availableStations: List<StationListItem> = emptyList()
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
