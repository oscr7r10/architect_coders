package com.oscar.moviesproject

import androidx.test.rule.GrantPermissionRule
import com.oscar.domain.movie.data.MoviesRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ExampleInstrumentedTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val locationPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        "android.permission.ACCESS_COARSE_LOCATION"
    )

    @Inject
    lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @Test
    fun test_it_works() {
        runBlocking {
            val movie = moviesRepository.movies.first()
            assertTrue(movie.isEmpty())
        }
    }
}