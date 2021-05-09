package com.patrykkosieradzki.ryanairandroidchallenge.ui.flights

import com.patrykkosieradzki.ryanairandroidchallenge.ui.FlightSearchFiltersParcel
import com.patrykkosieradzki.ryanairandroidchallenge.utils.FragmentScenarioRobot
import com.patrykkosieradzki.ryanairandroidchallenge.utils.RobotTest
import com.patrykkosieradzki.ryanairandroidchallenge.utils.declareMocksTestRule
import com.patrykkosieradzki.ryanairandroidchallenge.utils.mockViewModelRule
import org.junit.Rule
import org.junit.Test
import org.koin.test.mock.declareMock

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
            capture("03_Flights_list")
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
}
