package com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch

import com.patrykkosieradzki.ryanairandroidchallenge.R
import com.patrykkosieradzki.ryanairandroidchallenge.databinding.FlightSearchFragmentBinding
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseFragment

class FlightSearchFragment :
    BaseFragment<FlightSearchViewState, FlightSearchViewModel, FlightSearchFragmentBinding>(
        R.layout.flight_search_fragment, FlightSearchViewModel::class
    )
