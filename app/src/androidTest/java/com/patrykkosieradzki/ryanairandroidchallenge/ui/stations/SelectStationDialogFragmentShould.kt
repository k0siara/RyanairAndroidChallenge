package com.patrykkosieradzki.ryanairandroidchallenge.ui.stations

import com.patrykkosieradzki.ryanairandroidchallenge.R
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
            capture("01_Station_list_empty")
            setMockStations()
            capture("01_Station_list_filled")
            setMockFilter()
            capture("01_Station_list_filtered")
        }
    }

    override fun createRobot() = SelectStationDialogFragmentRobot()
}

class SelectStationDialogFragmentRobot :
    FragmentScenarioRobot<SelectStationViewState, SelectStationViewModel>() {
    fun startDialogFragment() {
        startDialogFragment { SelectStationDialogFragment() }
    }

    fun setMockStations() {
        setViewState(
            SelectStationViewState(
                availableStations = emptyList(),
                filteredStations = listOf(
                    StationHeaderItem("Poland"),
                    StationGroupListItem("Warsaw", "WAW"),
                    StationGroupListItem("Wroclaw", "WRO"),
                    StationGroupListItem("Katowice", "KTW"),
                    StationHeaderItem("London"),
                    StationGroupListItem("Heathrow", "LHR"),
                    StationGroupListItem("Gatwick", "LGW"),
                    StationGroupListItem("Stansted", "STN"),
                ),
                filter = "",
                inProgress = false
            )
        )
    }

    fun setMockFilter() {
        R.id.stationFilterEditText.inputText("Wro")
        setViewState(
            SelectStationViewState(
                availableStations = emptyList(),
                filteredStations = listOf(
                    StationHeaderItem("Poland"),
                    StationGroupListItem("Wroclaw", "WRO"),
                ),
                filter = "Wro",
                inProgress = false
            )
        )
    }
}
