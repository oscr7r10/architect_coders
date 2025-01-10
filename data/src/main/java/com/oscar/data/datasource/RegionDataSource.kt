package com.oscar.data.datasource

import com.oscar.domain.Location


interface RegionDataSource {
    suspend fun findLastRegion(): String
    suspend fun Location.toRegion(): String
}