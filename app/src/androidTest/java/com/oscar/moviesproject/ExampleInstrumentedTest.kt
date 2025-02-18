package com.oscar.moviesproject

import androidx.test.rule.GrantPermissionRule
import com.oscar.domain.movie.data.MoviesRepository
import com.oscar.framework.movie.database.DbMovie
import com.oscar.framework.movie.database.MoviesDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
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

    @Inject
    lateinit var moviesDao: MoviesDao

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

    @Test
    fun check_4_items_db()= runTest {
        moviesDao.saveMovies(buildDatabaseMovies(1,2,3,4))
        val movies = moviesDao.fetchPopularMovies().first()
        assertEquals(4, movies.size)
    }

    @Test
    fun check_6_items_db() = runTest {
        moviesDao.saveMovies(buildDatabaseMovies(1,2,3,4,5,6))
        val movies = moviesDao.fetchPopularMovies().first()
        assertEquals(6, movies.size)
    }
}

fun buildDatabaseMovies(vararg id: Int) = id.map {
    DbMovie(
        id = it,
        title = "Title $it",
        overview = "Overview $it",
        releaseDate = "01/01/2025",
        poster = "",
        backdrop = "",
        originalLanguage = "EN",
        originalTitle = "Original Title $it",
        popularity = 5.0,
        voteAverage = 5.1,
        favorite = false
    )
}