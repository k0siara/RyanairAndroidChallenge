package com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch

import com.patrykkosieradzki.ryanairandroidchallenge.domain.usecases.GetAllStationsUseCase
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseViewModel
import com.patrykkosieradzki.ryanairandroidchallenge.utils.ViewState

class FlightSearchViewModel(
    private val getAllStationsUseCase: GetAllStationsUseCase
) : BaseViewModel<FlightSearchViewState>(
    initialState = FlightSearchViewState(inProgress = false)
) {
    override fun initialize() {
        super.initialize()
    }
}

data class FlightSearchViewState(
    override val inProgress: Boolean,
) : ViewState {
    override fun toSuccess() = copy(inProgress = false)
}
