package com.github.ln12.library

import kotlinx.coroutines.flow.MutableStateFlow

expect class ConnectivityStatus {
    val isNetworkConnected: MutableStateFlow<Boolean>
    fun start()
    fun stop()
}