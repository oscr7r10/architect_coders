package com.oscar.moviesproject.data.datasource

import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.oscar.moviesproject.ui.common.getFromLocationCompat

const val DEFAULT_REGION = "US"

class RegionDataSource(
    application: Application,
    private val locationDataSource: LocationDataSource
) {

    private val geocoder = Geocoder(application)
    suspend fun findLastRegion(): String {
        val location = locationDataSource.findLastLocation()
        return location?.toRegion() ?: DEFAULT_REGION
    }

    private suspend fun Location.toRegion(): String{
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }

}