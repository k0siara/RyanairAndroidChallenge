package com.patrykkosieradzki.ryanairandroidchallenge.ui.flights

import com.patrykkosieradzki.ryanairandroidchallenge.ui.FlightSearchFiltersParcel
import com.patrykkosieradzki.ryanairandroidchallenge.utils.FragmentScenarioRobot
import com.patrykkosieradzki.ryanairandroidchallenge.utils.RobotTest
import com.patrykkosieradzki.ryanairandroidchallenge.utils.declareMocksTestRule
import com.patrykkosieradzki.ryanairandroidchallenge.utils.mockViewModelRule
import org.junit.Rule
import org.junit.Test
import org.koin.test.mock.declareMock
import org.threeten.bp.LocalDateTime

class FlightsListFragmentShould : RobotTest<FlightsListFragmentRobot>() {

    @get:Rule
    val mockRule = mockViewModelRule<FlightsListViewModel, FlightsListViewState>(
        defaultViewState = FlightsListViewState(
            inProgress = false
        )
    )

    @get:Rule
    val rule = declareMocksTestRule {
        declareMock<FlightsListViewModel>()
    }

    @Test
    fun showFlightsListFragment() {
        withRobot {
            startFragment()
            capture("03_Flights_list_empty")
            setMockData()
            capture("03_Flights_list_filled")
        }
    }

    override fun createRobot() = FlightsListFragmentRobot()
}

class FlightsListFragmentRobot :
    FragmentScenarioRobot<FlightsListViewState, FlightsListViewModel>() {
    fun startFragment() {
        startFragment(
            FlightsListFragmentArgs(
                FlightSearchFiltersParcel(
                    dateOut = "",
                    originCode = "",
                    destinationCode = "",
                    adults = 1,
                    teens = 0,
                    children = 0
                )
            ).toBundle()
        ) { FlightsListFragment() }
    }

    fun setMockData() {
        setViewState(
            FlightsListViewState(
                inProgress = false,
                dateOut = "2021-05-09",
                originCode = "WRO",
                destinationCode = "STN",
                flights = listOf(
                    FlightListItem(
                        originCode = "WRO",
                        originName = "Wroclaw",
                        destinationCode = "STN",
                        destinationName = "London Stansted",
                        duration = "02:20",
                        amount = 104.33F,
                        currency = "EUR",
                        flightNumber = "ABC 123",
                        startDateTime = LocalDateTime.of(2020, 1, 1, 10, 30),
                        endDateTime = LocalDateTime.of(2020, 1, 1, 15, 10),
                    ),
                    FlightListItem(
                        originCode = "WRO",
                        originName = "Wroclaw",
                        destinationCode = "STN",
                        destinationName = "London Stansted",
                        duration = "02:20",
                        amount = 104.33F,
                        currency = "EUR",
                        flightNumber = "ABC 123",
                        startDateTime = LocalDateTime.of(2020, 1, 3, 8, 30),
                        endDateTime = LocalDateTime.of(2020, 1, 3, 12, 0),
                    )
                )
            )
        )
    }
}
