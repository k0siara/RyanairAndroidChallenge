package com.patrykkosieradzki.ryanairandroidchallenge.ui.stations

import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.Station
import com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases.GetAllStationsUseCase
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseViewModel
import com.patrykkosieradzki.ryanairandroidchallenge.utils.ViewState
import com.patrykkosieradzki.ryanairandroidchallenge.utils.extensions.valueNN
import kotlinx.coroutines.CoroutineScope

class SelectStationViewModel(
    private val getAllStationsUseCase: GetAllStationsUseCase
) : BaseViewModel<SelectStationViewState>(
    initialState = SelectStationViewState(inProgress = true)
) {
    override fun initialize() {
        super.initialize()
        val block: suspend CoroutineScope.() -> Unit = {
            val stations = getAllStationsUseCase.invoke()
            val groupedStations = convertStationsToGroupedStationList(stations)

            updateViewState {
                it.copy(
                    availableStations = stations,
                    filteredStations = groupedStations,
                    inProgress = false,
                )
            }
        }
        safeLaunch(block)
    }

    private fun convertStationsToGroupedStationList(
        stations: List<Station>,
        searchFilter: String = ""
    ): List<StationListItem> {
        return stations.groupBy { it.countryName }.entries
            .map {
                ArrayList<StationListItem>().apply {
                    add(StationHeaderItem(it.key))
                    it.value.forEach { station ->
                        if (station.name.contains(searchFilter, ignoreCase = true)) {
                            add(
                                StationGroupListItem(
                                    "${station.name} (${station.code})",
                                    station.code
                                )
                            )
                        }
                    }
                }
            }.filter { it.size != 1 }.flatten()
    }

    fun updateStationsSearchResults(searchFilter: String) {
        updateViewState {
            it.copy(
                filter = searchFilter,
                filteredStations = convertStationsToGroupedStationList(
                    viewState.valueNN.availableStations,
                    searchFilter = searchFilter
                )
            )
        }
    }
}

data class SelectStationViewState(
    val availableStations: List<Station> = emptyList(),
    val filteredStations: List<StationListItem> = emptyList(),
    val filter: String = "",
    override val inProgress: Boolean,
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
