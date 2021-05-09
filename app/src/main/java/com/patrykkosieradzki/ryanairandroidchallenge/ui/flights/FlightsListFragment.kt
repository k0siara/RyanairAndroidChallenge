package com.patrykkosieradzki.ryanairandroidchallenge.ui.flights

import android.view.View
import androidx.navigation.fragment.navArgs
import com.patrykkosieradzki.ryanairandroidchallenge.BR
import com.patrykkosieradzki.ryanairandroidchallenge.R
import com.patrykkosieradzki.ryanairandroidchallenge.databinding.FlightsListFragmentBinding
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseFragment
import me.tatarka.bindingcollectionadapter2.ItemBinding

class FlightsListFragment :
    BaseFragment<FlightsListViewState, FlightsListViewModel, FlightsListFragmentBinding>(
        R.layout.flights_list_fragment, FlightsListViewModel::class
    ) {

    val args: FlightsListFragmentArgs by navArgs()

    private val itemBinding = ItemBinding.of<FlightListItem>(BR.item, R.layout.flight_list_item)

    override fun setupViews(view: View) {
        super.setupViews(view)
        with(binding) {
            binding.itemBinding = this@FlightsListFragment.itemBinding
            toolbar.setNavigationOnClickListener {
                onBackEvent.invoke()
            }
        }
        viewModel.searchForFlights(args.flightSearchFilters)
    }
}
