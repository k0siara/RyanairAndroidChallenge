package com.patrykkosieradzki.ryanairandroidchallenge.ui.selectstation

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.patrykkosieradzki.ryanairandroidchallenge.BR
import com.patrykkosieradzki.ryanairandroidchallenge.R
import com.patrykkosieradzki.ryanairandroidchallenge.databinding.SelectStationDialogFragmentBinding
import com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch.FlightSearchFragment.Companion.RECEIVE_STATION_BUNDLE_STATION_CODE_KEY
import com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch.FlightSearchFragment.Companion.RECEIVE_STATION_BUNDLE_STATION_NAME_KEY
import com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch.FlightSearchFragment.Companion.RECEIVE_STATION_REQUEST_KEY
import com.patrykkosieradzki.ryanairandroidchallenge.utils.BaseDialogFragment
import com.patrykkosieradzki.ryanairandroidchallenge.utils.OnItemClickListener
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind

class SelectStationDialogFragment :
    BaseDialogFragment<SelectStationViewState,
        SelectStationViewModel, SelectStationDialogFragmentBinding>(
        R.layout.select_station_dialog_fragment, SelectStationViewModel::class
    ) {

    private val onItemBind = OnItemBind<StationListItem> { itemBinding, _, item ->
        itemBinding.set(
            BR.item,
            if (item is StationHeaderItem)
                R.layout.station_list_header else R.layout.station_list_item
        )
    }

    private val itemBinding = ItemBinding.of(onItemBind)
        .bindExtra(
            BR.listener,
            object : OnItemClickListener<StationListItem> {
                override fun onClick(item: StationListItem) {
                    val groupItem = item as StationGroupListItem
                    setFragmentResult(
                        RECEIVE_STATION_REQUEST_KEY,
                        bundleOf(
                            RECEIVE_STATION_BUNDLE_STATION_NAME_KEY to groupItem.name,
                            RECEIVE_STATION_BUNDLE_STATION_CODE_KEY to groupItem.code
                        )
                    )
                    dialog?.dismiss()
                }
            }
        )

    override fun setupViews(view: View) {
        super.setupViews(view)
        binding.itemBinding = itemBinding
    }

    companion object {
        fun newInstance() = SelectStationDialogFragment()
    }
}
