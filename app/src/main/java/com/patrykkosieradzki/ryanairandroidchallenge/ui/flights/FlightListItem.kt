package com.patrykkosieradzki.ryanairandroidchallenge.ui.flights

import org.threeten.bp.LocalDateTime

data class FlightListItem(
    val originCode: String,
    val originName: String,
    val destinationCode: String,
    val destinationName: String,
    val duration: String,
    val amount: Float,
    val currency: String,
    val flightNumber: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
)
