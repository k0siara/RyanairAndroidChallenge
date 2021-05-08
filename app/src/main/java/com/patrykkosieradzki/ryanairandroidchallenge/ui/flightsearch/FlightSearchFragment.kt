package com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch

import android.app.DatePickerDialog
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.patrykkosieradzki.ryanairandroidchallenge.R
import com.patrykkosieradzki.ryanairandroidchallenge.databinding.FlightSearchFragmentBinding
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseFragment
import com.patrykkosieradzki.ryanairandroidchallenge.utils.extensions.valueNN
import org.threeten.bp.LocalDate

class FlightSearchFragment :
    BaseFragment<FlightSearchViewState, FlightSearchViewModel, FlightSearchFragmentBinding>(
        R.layout.flight_search_fragment, FlightSearchViewModel::class
    ) {

    override fun setupViews(view: View) {
        super.setupViews(view)
        viewModel.apply {
            departureDateChangeEvent.observe(viewLifecycleOwner) {
                showDepartureDatePickedDialog()
            }
        }

        binding.apply {
            setSpinner(adultsSpinner) { viewModel.updateAdultsSelected(it) }
            setSpinner(teensSpinner) { viewModel.updateTeensSelected(it) }
            setSpinner(childrenSpinner) { viewModel.updateChildrenSelected(it) }

            priceSeekbar.apply {
                setRangeValues(0, viewModel.viewState.valueNN.maxFlightSoldPrice)
                selectedMinValue = 0
                selectedMaxValue = viewModel.viewState.valueNN.meanFlightSoldPrice
                setOnRangeSeekBarChangeListener { _, minValue, maxValue ->
                    viewModel.updateMinAndMaxPriceValue(minValue as Int, maxValue as Int)
                }
            }
        }
    }

    private fun setSpinner(spinner: Spinner, selectionCallback: (Int) -> Unit) {
        spinner.apply {
            adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.people_number_array,
                android.R.layout.simple_spinner_item
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        }.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                pos: Int,
                id: Long
            ) {
                selectionCallback.invoke(parent.getItemAtPosition(pos).toString().toInt())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun showDepartureDatePickedDialog() {
        val departureDate = viewModel.viewState.valueNN.departureDate

        DatePickerDialog(
            requireActivity(),
            { _, year, monthOfYear, dayOfMonth ->
                viewModel.updateDepartureDate(LocalDate.of(year, monthOfYear, dayOfMonth))
            },
            departureDate.year, departureDate.monthValue, departureDate.year
        ).show()
    }
}
