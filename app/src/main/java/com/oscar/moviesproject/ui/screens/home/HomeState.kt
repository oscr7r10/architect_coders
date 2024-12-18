package com.oscar.moviesproject.ui.screens.home

import android.Manifest
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.oscar.moviesproject.ui.common.PermissionRequestEffect
import com.oscar.moviesproject.ui.common.getRegion
import kotlinx.coroutines.launch

class HomeState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val scrollBehavior: TopAppBarScrollBehavior
) {

    @Composable
    fun AskRegionEffect(onRegion: (String) -> Unit){

        val ctx = LocalContext.current.applicationContext
        val coroutineScope = rememberCoroutineScope()
        PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) { granted ->
            coroutineScope.launch {
                val region = if (granted) ctx.getRegion() else "US"
                onRegion(region)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberHomeState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
): HomeState {
    return remember(scrollBehavior) { HomeState(scrollBehavior) }
}