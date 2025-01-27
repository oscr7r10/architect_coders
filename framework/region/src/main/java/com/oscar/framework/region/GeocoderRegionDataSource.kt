package com.oscar.framework.region

import android.location.Geocoder
import com.oscar.domain.region.data.RegionDataSource
import com.oscar.domain.region.entities.Location
import com.oscar.domain.region.data.LocationDataSource
import javax.inject.Inject

const val DEFAULT_REGION = "US"

class GeocoderRegionDataSource @Inject constructor(
    private val geocoder: Geocoder,
    private val locationDataSource: LocationDataSource
) : RegionDataSource {

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