package com.patrykkosieradzki.ryanairandroidchallenge.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.patrykkosieradzki.ryanairandroidchallenge.RyanairAndroidChallengeDispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

abstract class BaseJunit4Test {
    protected val testCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun baseSetup() {
        MockitoAnnotations.initMocks(this)
        RyanairAndroidChallengeDispatchers.IO = testCoroutineDispatcher
    }

    @After
    fun baseTearDown() {
        RyanairAndroidChallengeDispatchers.resetAll()
    }
}
