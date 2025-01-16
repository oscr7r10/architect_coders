package com.oscar.region.data

class RegionRepository(
    private val regionDataSource: RegionDataSource
) {
    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()

}