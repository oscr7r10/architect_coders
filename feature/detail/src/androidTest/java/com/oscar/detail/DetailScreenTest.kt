package com.oscar.detail

import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.oscar.feature.common.Result
import com.oscar.feature.common.components.LOADING_INDICATOR_TAG
import com.oscar.feature.detail.DetailScreen
import com.oscar.test.unit.sampleMovie
import junit.framework.TestCase.assertTrue
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLoadingState_showProgress(): Unit = with(composeTestRule){
        setContent {
            DetailScreen(
                state = Result.Loading ,
                onBack = { /*TODO*/ }
            ) {  }
        }
        onNodeWithTag(LOADING_INDICATOR_TAG).assertExists()
    }

    @Test
    fun whenErrorState_showError(): Unit = with(composeTestRule){
        setContent {
            DetailScreen(
                state = Result.Error(RuntimeException("An error occurred")),
                onBack = { /*TODO*/ }
            ) {}
        }
        onNodeWithText("An error occurred").assertExists()
    }

    @Test
    fun whenSuccessState_movieIsShown(): Unit = with(composeTestRule) {
        setContent {
            DetailScreen(
                state = Result.Success(sampleMovie(2)),
                onBack = {},
                onFavoriteClicked = {}
            )
        }

        onNodeWithText("Title 2").assertExists()
    }

    @Test
    fun whenFavoriteClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var clicked = false
        setContent {
            DetailScreen(
                state = Result.Success(sampleMovie(2)),
                onBack = { /*TODO*/ }
            ) {
                clicked = true
            }
        }
        onNodeWithTag("floating").performClick()
        assertTrue(clicked)
    }

    @Test
    fun whenBackClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var clicked = false
        setContent {
            DetailScreen(
                state = Result.Success(sampleMovie(2)),
                onBack = { clicked = true },
                onFavoriteClicked = {}
            )
        }

        onNodeWithTag("Back").performClick()
        assertTrue(clicked)
    }

}

private fun getStringResource(@StringRes id: Int): String {
    val ctx = InstrumentationRegistry.getInstrumentation().targetContext
    return ctx.getString(id)
}