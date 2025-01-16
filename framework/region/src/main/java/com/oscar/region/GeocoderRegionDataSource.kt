package com.oscar.region

import android.location.Geocoder
import com.oscar.region.data.LocationDataSource

const val DEFAULT_REGION = "US"

class GeocoderRegionDataSource(
    private val geocoder: Geocoder,
    private val locationDataSource: LocationDataSource
) : com.oscar.region.data.RegionDataSource {

    override suspend fun findLastRegion(): String {
        val location = locationDataSource.findLastLocation()
        return location?.toRegion() ?: DEFAULT_REGION
    }

    override suspend fun com.oscar.region.entities.Location.toRegion(): String{
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }

}