package com.patrykkosieradzki.ryanairandroidchallenge

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object RyanairAndroidChallengeDispatchers {
    var Main: CoroutineDispatcher = Dispatchers.Main
    var IO: CoroutineDispatcher = Dispatchers.IO
    var Default = Dispatchers.Default

    fun resetAll() {
        Main = Dispatchers.Main
        IO = Dispatchers.IO
        Default = Dispatchers.Default
    }
}
