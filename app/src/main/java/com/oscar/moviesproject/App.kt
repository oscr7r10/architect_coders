package com.oscar.moviesproject

import android.app.Application
import androidx.room.Room
import com.oscar.domain.movie.DomainMovieModule
import com.oscar.domain.region.DomainRegionModule
import com.oscar.feature.detail.FeatureDetailModule
import com.oscar.feature.home.FeatureHomeModule
import com.oscar.framework.core.MoviesDatabase
import com.oscar.framework.core.frameworkCoreModule
import com.oscar.framework.movie.FrameworkMovieModule
import com.oscar.framework.region.FrameworkRegionModule
import com.oscar.framework.region.frameworkRegionModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ksp.generated.module

class App : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                DomainMovieModule().module,
                DomainRegionModule().module,
                FeatureHomeModule().module,
                FeatureDetailModule().module,
                frameworkCoreModule,
                FrameworkMovieModule().module,
                frameworkRegionModule,
                FrameworkRegionModule().module
            )
        }

    }
}

val appModule = module {
    single(named("apiKey")) { BuildConfig.TMDB_API_KEY }
}