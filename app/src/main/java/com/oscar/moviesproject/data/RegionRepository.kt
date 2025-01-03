package com.oscar.moviesproject.data

import com.oscar.moviesproject.data.datasource.RegionDataSource


class RegionRepository(
    private val regionDataSource: RegionDataSource
) {
    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()

}