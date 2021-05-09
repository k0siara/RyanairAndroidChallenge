package com.patrykkosieradzki.ryanairandroidchallenge.ui.search

import com.google.common.truth.Truth.assertThat
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseJunit4Test
import com.patrykkosieradzki.ryanairandroidchallenge.utils.extensions.valueNN
import com.patrykkosieradzki.ryanairandroidchallenge.utils.verifyEventFired
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDate

class FlightSearchViewModelTest : BaseJunit4Test() {
    lateinit var viewModel: FlightSearchViewModel

    @Before
    fun setUp() {
        viewModel = FlightSearchViewModel()
    }

    @Test
    fun `on departure date clicked should open dialog`() {
        viewModel.onDepartureDateClicked()

        viewModel.departureDateChangeEvent.verifyEventFired()
    }

    @Test
    fun `should update adults`() {
        viewModel.updateAdultsSelected(5)

        assertThat(viewModel.viewState.valueNN.adultsSelected).isEqualTo(5)
    }

    @Test
    fun `should update teens`() {
        viewModel.updateTeensSelected(2)

        assertThat(viewModel.viewState.valueNN.teensSelected).isEqualTo(2)
    }

    @Test
    fun `should update children`() {
        viewModel.updateChildrenSelected(3)

        assertThat(viewModel.viewState.valueNN.childrenSelected).isEqualTo(3)
    }

    @Test
    fun `should update departure date`() {
        val now = LocalDate.now()
        viewModel.updateDepartureDate(now)

        assertThat(viewModel.viewState.valueNN.departureDate).isEqualTo(now)
    }

    @Test
    fun `should update min and max price values`() {
        viewModel.updateMinAndMaxPriceValue(5, 10)

        assertThat(viewModel.viewState.valueNN.selectedMinPrice).isEqualTo(5)
        assertThat(viewModel.viewState.valueNN.selectedMaxPrice).isEqualTo(10)
    }

    @Test
    fun `should choose departure station on departure field clicked`() {
        viewModel.onDepartureDateClicked()

        viewModel.departureDateChangeEvent.verifyEventFired()
    }

    @Test
    fun `should choose arrival station on arrival field clicked`() {
        viewModel.onArrivalFieldClicked()

        viewModel.chooseArrivalStationEvent.verifyEventFired()
    }

    @Test
    fun `should update departure station`() {
        viewModel.updateDepartureStation("Wroclaw", "WRO")

        assertThat(viewModel.viewState.valueNN.selectedDepartureStationName).isEqualTo("Wroclaw")
        assertThat(viewModel.viewState.valueNN.selectedDepartureStationCode).isEqualTo("WRO")
    }

    @Test
    fun `should update arrival station`() {
        viewModel.updateArrivalStation("Wroclaw", "WRO")

        assertThat(viewModel.viewState.valueNN.selectedArrivalStationName).isEqualTo("Wroclaw")
        assertThat(viewModel.viewState.valueNN.selectedArrivalStationCode).isEqualTo("WRO")
    }

    @Test
    fun `on search button clicked should show toast when form is not valid`() {
        viewModel.onSearchButtonClicked()

        viewModel.showToastEvent.verifyEventFired(FlightSearchViewModel.FILL_SEARCH_DATA_MESSAGE)
    }

    @Test
    fun `on search button clicked should search for flights when form is valid`() {
        viewModel.updateDepartureStation("Wroclaw", "WRO")
        viewModel.updateArrivalStation("London", "LDN")
        viewModel.updateAdultsSelected(5)

        viewModel.onSearchButtonClicked()

        viewModel.searchForFlightsEvent.verifyEventFired()
    }
}
