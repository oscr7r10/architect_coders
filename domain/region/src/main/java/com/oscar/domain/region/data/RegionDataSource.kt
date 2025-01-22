package com.oscar.domain.region.data

import com.oscar.domain.region.entities.Location

interface RegionDataSource {
    suspend fun findLastRegion(): String
    suspend fun Location.toRegion(): String
}