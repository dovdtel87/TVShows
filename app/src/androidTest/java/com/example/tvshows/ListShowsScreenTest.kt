package com.example.tvshows

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.tvshows.tvshows.domain.usecases.FetchShowsUseCase
import com.example.tvshows.tvshows.ui.ListShowsViewModel
import com.example.tvshows.tvshows.ui.list.screen.ListShowsScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class ListShowsScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Inject
    lateinit var fetchShowsUseCase: FetchShowsUseCase

    @Before
    fun init() {
        hiltRule.inject()
    }


    @Test
    fun testListShowsScreen_Success() = runTest {
        composeTestRule.setContent {
            ListShowsScreen(
                viewModel = ListShowsViewModel(fetchShowsUseCase)
            )
        }

        composeTestRule.onNodeWithText("Batman").assertExists()
        composeTestRule.onNodeWithText("Superman").assertExists()
        composeTestRule.onNodeWithText("Friends").assertExists()
    }
}
