package com.oscar.domain.region.data

import org.koin.core.annotation.Factory

@Factory
class RegionRepository(
    private val regionDataSource: RegionDataSource
) {
    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()

}