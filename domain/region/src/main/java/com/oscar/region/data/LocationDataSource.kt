package com.oscar.region.data

import com.oscar.region.entities.Location


interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}