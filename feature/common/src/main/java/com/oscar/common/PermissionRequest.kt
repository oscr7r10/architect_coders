package com.oscar.common

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@Composable
fun PermissionRequestEffect(permission: String, onResult: (Boolean) -> Unit) {
    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
            onResult(it)
        }

    LaunchedEffect(key1 = Unit) {
        permissionLauncher.launch(permission)
    }
}