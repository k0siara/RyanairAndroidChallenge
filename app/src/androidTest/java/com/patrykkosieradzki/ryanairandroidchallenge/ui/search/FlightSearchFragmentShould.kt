package com.patrykkosieradzki.ryanairandroidchallenge.ui.search

import com.patrykkosieradzki.ryanairandroidchallenge.utils.FragmentScenarioRobot
import com.patrykkosieradzki.ryanairandroidchallenge.utils.RobotTest
import com.patrykkosieradzki.ryanairandroidchallenge.utils.declareMocksTestRule
import com.patrykkosieradzki.ryanairandroidchallenge.utils.mockViewModelRule
import org.junit.Rule
import org.junit.Test
import org.koin.test.mock.declareMock

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
            capture("02_Flight_search")
        }
    }

    override fun createRobot() = FlightSearchFragmentRobot()
}

class FlightSearchFragmentRobot() :
    FragmentScenarioRobot<FlightSearchViewState, FlightSearchViewModel>() {
    fun startFragment() {
        startFragment { FlightSearchFragment() }
    }
}
