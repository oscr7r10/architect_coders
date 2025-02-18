package com.oscar.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.oscar.feature.common.Result
import com.oscar.feature.common.components.LOADING_INDICATOR_TAG
import com.oscar.feature.home.HomeScreen
import com.oscar.test.unit.sampleMovies
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLoadingState_showProgress(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                state = Result.Loading,
                onClick = {}
            )
        }
        onNodeWithTag(LOADING_INDICATOR_TAG).assertIsDisplayed()
    }

    @Test
    fun whenErrorState_showError() : Unit = with(composeTestRule){
        setContent {
            HomeScreen(
                onClick = {},
                state = Result.Error(RuntimeException("An error ocurred"))
            )
        }

        onNodeWithText("An error ocurred").assertExists()
    }

    @Test
    fun whenSuccessState_showMovies() : Unit = with(composeTestRule){
        setContent {
            HomeScreen(
                onClick = {},
                state = Result.Success(sampleMovies(1, 2, 3))
            )
        }
        onNodeWithText("Title 2").assertExists()
    }

    @Test
    fun whenMovieClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var clickedMovieId = -1
        val movies = sampleMovies(1, 2, 3)
        setContent {
            HomeScreen(
                state = Result.Success(movies),
                onClick = {
                    clickedMovieId = it.id
                }
            )
        }

        //onNodeWithText("Title 2").performClick()
        onNodeWithTag("MOVIE_ELEMENT 2").performClick()

        assertEquals(2, clickedMovieId)
    }
}