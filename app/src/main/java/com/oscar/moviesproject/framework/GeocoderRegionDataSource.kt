package com.oscar.moviesproject.framework

import android.location.Geocoder
import com.oscar.domain.Location
import com.oscar.data.datasource.LocationDataSource
import com.oscar.data.datasource.RegionDataSource
import com.oscar.moviesproject.ui.common.getFromLocationCompat

const val DEFAULT_REGION = "US"

class GeocoderRegionDataSource(
    private val geocoder: Geocoder,
    private val locationDataSource: com.oscar.data.datasource.LocationDataSource
) : com.oscar.data.datasource.RegionDataSource {

    override suspend fun findLastRegion(): String {
        val location = locationDataSource.findLastLocation()
        return location?.toRegion() ?: DEFAULT_REGION
    }

    override suspend fun Location.toRegion(): String{
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }

}