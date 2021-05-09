package com.patrykkosieradzki.ryanairandroidchallenge.utils

import com.google.common.truth.Truth.assertThat
import com.hadilq.liveevent.LiveEvent
import com.patrykkosieradzki.ryanairandroidchallenge.utils.extensions.valueNN

fun LiveEvent<Unit>.verifyEventFired() {
    assertThat(this.valueNN).isEqualTo(Unit)
}

fun <T> LiveEvent<T>.verifyEventFired(t: T) {
    assertThat(this.valueNN).isEqualTo(t)
}
