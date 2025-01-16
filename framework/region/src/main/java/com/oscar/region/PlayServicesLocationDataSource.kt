package com.oscar.region

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.oscar.region.entities.Location
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import android.location.Location as AndroidLocation

class PlayServicesLocationDataSource(
    private val fusedLocationClient: FusedLocationProviderClient
) : com.oscar.region.data.LocationDataSource {

    override suspend fun findLastLocation(): Location? = fusedLocationClient.lastLocation()

    @SuppressLint("MissingPermission")
    private suspend fun FusedLocationProviderClient.lastLocation() : Location?{
        return suspendCancellableCoroutine { continuation ->
            lastLocation.addOnSuccessListener { location ->
                continuation.resume(location.toDomainLocation())
            }.addOnFailureListener{
                continuation.resume(null)
            }
        }
    }
}

private fun AndroidLocation.toDomainLocation(): Location =
    Location(latitude, longitude)