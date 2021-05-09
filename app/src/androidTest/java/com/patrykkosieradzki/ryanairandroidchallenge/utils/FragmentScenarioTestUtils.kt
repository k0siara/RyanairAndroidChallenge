package com.patrykkosieradzki.ryanairandroidchallenge.utils

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import com.patrykkosieradzki.ryanairandroidchallenge.R
import org.hamcrest.CoreMatchers.not
import org.junit.rules.TestRule
import org.junit.runners.model.Statement

fun declareMocksTestRule(stubbing: () -> Unit): TestRule = TestRule { base, _ ->
    object : Statement() {
        override fun evaluate() {
            stubbing()
            base.evaluate()
        }
    }
}

open class FragmentScenarioRobot<STATE : ViewState, VM : BaseViewModel<STATE>> : Robot() {

    lateinit var scenario: FragmentScenario<BaseFragment<STATE, VM, *>>
    lateinit var dialogFragmentScenario: FragmentScenario<BaseDialogFragment<STATE, VM, *>>

    @Suppress("UNCHECKED_CAST")
    inline fun <reified F : BaseFragment<STATE, VM, *>> startFragment(
        args: Bundle? = null,
        crossinline instantiateFragment: () -> F
    ) {
        scenario = launchFragmentInContainer(
            fragmentArgs = args,
            themeResId = R.style.Theme_Ryanairandroidchallenge,
            instantiate = instantiateFragment
        ) as FragmentScenario<BaseFragment<STATE, VM, *>>
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified F : BaseDialogFragment<STATE, VM, *>> startDialogFragment(
        args: Bundle? = null,
        crossinline instantiateFragment: () -> F
    ) {
        dialogFragmentScenario = launchFragmentInContainer(
            fragmentArgs = args,
            themeResId = R.style.Theme_Ryanairandroidchallenge,
            instantiate = instantiateFragment
        ) as FragmentScenario<BaseDialogFragment<STATE, VM, *>>
    }

    fun onViewModel(actions: VM.() -> Unit) {
        if (::scenario.isInitialized) {
            scenario.onFragment {
                it.viewModel.actions()
            }
        } else if (::dialogFragmentScenario.isInitialized) {
            dialogFragmentScenario.onFragment {
                it.viewModel.actions()
            }
        }
    }

    fun setViewState(state: STATE) {
        onViewModel {
            val viewState = viewState as MutableLiveData
            viewState.value = state
        }
    }

    fun checkWasToastShown(text: String) {
        scenario.onFragment {
            Espresso.onView(ViewMatchers.withText(text))
                .inRoot(RootMatchers.withDecorView(not(it.requireActivity().window.decorView)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
}
