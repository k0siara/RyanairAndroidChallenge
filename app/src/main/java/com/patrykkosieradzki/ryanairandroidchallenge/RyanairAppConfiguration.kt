package com.patrykkosieradzki.ryanairandroidchallenge

import com.patrykkosieradzki.ryanairandroidchallenge.domain.AppConfiguration

class RyanairAppConfiguration : AppConfiguration {
    override val debug = BuildConfig.DEBUG
    override val tripTestApiUrl = "https://tripstest.ryanair.com/"
    override val ryanairApiUrl = "https://www.ryanair.com/api/"
}