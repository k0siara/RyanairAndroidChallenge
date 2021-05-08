package com.patrykkosieradzki.ryanairandroidchallenge.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onClick")
fun View.setOnClick(action: () -> Unit) {
    setOnClickListener { action.invoke() }
}

@BindingAdapter("visibleInvisible")
fun View.setVisibleInvisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}
