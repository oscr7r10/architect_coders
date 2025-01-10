package com.oscar.data.datasource

import com.oscar.domain.Location


interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}