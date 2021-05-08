package com.patrykkosieradzki.ryanairandroidchallenge.ui.selectstation

import com.hadilq.liveevent.LiveEvent
import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.Station
import com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases.GetAllStationsUseCase
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseViewModel
import com.patrykkosieradzki.ryanairandroidchallenge.utils.ViewState

class SelectStationViewModel(
    private val getAllStationsUseCase: GetAllStationsUseCase
) : BaseViewModel<SelectStationViewState>(
    initialState = SelectStationViewState(inProgress = true)
) {
    val closeDialogEvent = LiveEvent<Unit>()

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
                    it.value.forEach { station ->
                        add(
                            StationGroupListItem(
                                "${station.name} (${station.code})",
                                station.code
                            )
                        )
                    }
                }
            }.flatten()
    }
}

data class SelectStationViewState(
    val availableStations: List<StationListItem> = emptyList(),
    override val inProgress: Boolean,
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
