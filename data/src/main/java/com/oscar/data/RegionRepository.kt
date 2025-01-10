package com.oscar.data

import com.oscar.data.datasource.RegionDataSource


class RegionRepository(
    private val regionDataSource: RegionDataSource
) {
    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()

}