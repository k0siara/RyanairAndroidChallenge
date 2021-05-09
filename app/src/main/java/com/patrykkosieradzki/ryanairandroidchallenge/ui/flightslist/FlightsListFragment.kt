package com.patrykkosieradzki.ryanairandroidchallenge.ui.flightslist

import android.view.View
import androidx.navigation.fragment.navArgs
import com.patrykkosieradzki.ryanairandroidchallenge.R
import com.patrykkosieradzki.ryanairandroidchallenge.databinding.FlightsListFragmentBinding
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseFragment

class FlightsListFragment :
    BaseFragment<FlightsListViewState, FlightsListViewModel, FlightsListFragmentBinding>(
        R.layout.flights_list_fragment, FlightsListViewModel::class
    ) {

    val args: FlightsListFragmentArgs by navArgs()

    override fun setupViews(view: View) {
        super.setupViews(view)
        viewModel.apply {
        }

        binding.apply {
        }

        viewModel.searchForFlights(args.flightSearchFilters)
    }


}
