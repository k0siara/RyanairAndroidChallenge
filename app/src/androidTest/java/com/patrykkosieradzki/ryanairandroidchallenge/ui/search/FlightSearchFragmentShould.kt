package com.patrykkosieradzki.ryanairandroidchallenge.ui.search

import com.patrykkosieradzki.ryanairandroidchallenge.utils.FragmentScenarioRobot
import com.patrykkosieradzki.ryanairandroidchallenge.utils.RobotTest
import com.patrykkosieradzki.ryanairandroidchallenge.utils.declareMocksTestRule
import com.patrykkosieradzki.ryanairandroidchallenge.utils.mockViewModelRule
import org.junit.Rule
import org.junit.Test
import org.koin.test.mock.declareMock
import org.threeten.bp.LocalDate

class FlightSearchFragmentShould : RobotTest<FlightSearchFragmentRobot>() {

    @get:Rule
    val mockRule = mockViewModelRule<FlightSearchViewModel, FlightSearchViewState>(
        defaultViewState = FlightSearchViewState(
            inProgress = false
        )
    )

    @get:Rule
    val rule = declareMocksTestRule {
        declareMock<FlightSearchViewModel>()
    }

    @Test
    fun showStationDialogFragment() {
        withRobot {
            startFragment()
            capture("02_Flight_search_empty")
            setMockData()
            capture("02_Flight_search_filled")
        }
    }

    override fun createRobot() = FlightSearchFragmentRobot()
}

class FlightSearchFragmentRobot() :
    FragmentScenarioRobot<FlightSearchViewState, FlightSearchViewModel>() {
    fun startFragment() {
        startFragment { FlightSearchFragment() }
    }

    fun setMockData() {
        setViewState(
            FlightSearchViewState(
                inProgress = false,
                selectedDepartureStationName = "Wroclaw",
                selectedDepartureStationCode = "WRO",
                selectedArrivalStationName = "London Stansted",
                selectedArrivalStationCode = "STN",
                adultsSelected = 2,
                teensSelected = 1,
                childrenSelected = 0,
                departureDate = LocalDate.now(),
                maxFlightSoldPrice = FlightSearchViewModel.MAX_SOLD_FLIGHTS,
                meanFlightSoldPrice = FlightSearchViewModel.MEAN_SOLD_FLIGHTS,
                selectedMinPrice = 0,
                selectedMaxPrice = FlightSearchViewModel.MEAN_SOLD_FLIGHTS
            )
        )
    }
}
