package com.oscar.region.data

import com.oscar.region.entities.Location

interface RegionDataSource {
    suspend fun findLastRegion(): String
    suspend fun Location.toRegion(): String
}