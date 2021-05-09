package com.patrykkosieradzki.ryanairandroidchallenge.domain.model

open class FlightSearchFilters(
    open val dateOut: String,
    open val originCode: String,
    open val destinationCode: String,
    open val adults: Int,
    open val teens: Int,
    open val children: Int
)
