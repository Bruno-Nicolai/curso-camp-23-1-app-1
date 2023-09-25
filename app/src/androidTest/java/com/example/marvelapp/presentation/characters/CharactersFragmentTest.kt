package com.example.marvelapp.presentation.characters

import com.example.marvelapp.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marvelapp.extension.asJsonString
import com.example.marvelapp.framework.di.BaseUrlModule
import com.example.marvelapp.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@UninstallModules(BaseUrlModule::class)
@HiltAndroidTest
class CharactersFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer().apply {
            start(8080)
        }
        launchFragmentInHiltContainer<CharactersFragment>()
    }

    @Test
    fun shouldShowCharactersWhenViewIsCreated() {
        // FASTER/SMARTER
        // doesn't depend on there is internet network
        server.enqueue(MockResponse().setBody("characters_p1.json".asJsonString()))
        // even before a request is made, my test already receives data which inflates the rv

        onView(
            withId(R.id.rv_characters)
        ).check(matches(isDisplayed()))
    }

    @Test
    fun shouldLoadMoreCharactersWhenNewPageIsRequested() {
        // Arrange
        with(server) {
            enqueue(MockResponse().setBody("characters_p1.json".asJsonString()))
            enqueue(MockResponse().setBody("characters_p2.json".asJsonString()))
        }

        // Action
        onView(
            withId(R.id.rv_characters)
        ).perform(
            RecyclerViewActions
                .scrollToPosition<CharactersViewHolder>(21)
        )

        // Assert
        onView(
            withText("Ajak")
        ).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowErrorViewWhenAPIGoesOnError() {
        // Arrange
        server.enqueue(MockResponse().setResponseCode(404))
//            .setBody("characters_p1.json".asJsonString()))

        onView(
            withId(R.id.tv_initial_loading_error)
        ).check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

}