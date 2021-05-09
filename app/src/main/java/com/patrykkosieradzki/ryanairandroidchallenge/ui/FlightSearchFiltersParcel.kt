package com.patrykkosieradzki.ryanairandroidchallenge.ui

import android.os.Parcelable
import com.patrykkosieradzki.ryanairandroidchallenge.domain.model.FlightSearchFilters
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlightSearchFiltersParcel(
    override val dateOut: String,
    override val originCode: String,
    override val destinationCode: String,
    override val adults: Int,
    override val teens: Int,
    override val children: Int
) : FlightSearchFilters(dateOut, originCode, destinationCode, adults, teens, children), Parcelable
