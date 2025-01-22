package com.oscar.domain.region.data

import com.oscar.domain.region.entities.Location


interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}