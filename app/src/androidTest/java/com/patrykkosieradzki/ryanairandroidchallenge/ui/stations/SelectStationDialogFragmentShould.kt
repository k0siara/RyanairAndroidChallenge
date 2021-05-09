package com.patrykkosieradzki.ryanairandroidchallenge.ui.stations

import com.patrykkosieradzki.ryanairandroidchallenge.utils.FragmentScenarioRobot
import com.patrykkosieradzki.ryanairandroidchallenge.utils.RobotTest
import com.patrykkosieradzki.ryanairandroidchallenge.utils.declareMocksTestRule
import com.patrykkosieradzki.ryanairandroidchallenge.utils.mockViewModelRule
import org.junit.Rule
import org.junit.Test
import org.koin.test.mock.declareMock

class SelectStationDialogFragmentShould : RobotTest<SelectStationDialogFragmentRobot>() {

    @get:Rule
    val mockRule = mockViewModelRule<SelectStationViewModel, SelectStationViewState>(
        defaultViewState = SelectStationViewState(
            inProgress = false
        )
    )

    @get:Rule
    val rule = declareMocksTestRule {
        declareMock<SelectStationViewModel>()
    }

    @Test
    fun showStationDialogFragment() {
        withRobot {
            startDialogFragment()
            capture("01_Station_list")
        }
    }

    override fun createRobot() = SelectStationDialogFragmentRobot()
}

class SelectStationDialogFragmentRobot() :
    FragmentScenarioRobot<SelectStationViewState, SelectStationViewModel>() {
    fun startDialogFragment() {
        startDialogFragment { SelectStationDialogFragment() }
    }
}
