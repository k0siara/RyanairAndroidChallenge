package com.patrykkosieradzki.ryanairandroidchallenge.domain

interface AppConfiguration {
    val debug: Boolean
    val tripTestApiUrl: String
    val ryanairApiUrl: String
}
