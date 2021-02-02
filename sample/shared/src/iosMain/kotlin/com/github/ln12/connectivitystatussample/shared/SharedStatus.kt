package com.github.ln12.connectivitystatussample.shared

import com.github.ln12.library.ConnectivityStatus
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

actual class SharedStatus {
    private val connectivityStatus = ConnectivityStatus()
    actual val current = connectivityStatus.isNetworkConnected

    actual fun start() {
        connectivityStatus.start()
    }

    actual fun stop() {
        connectivityStatus.stop()
    }

    fun getStatus(success: (Boolean) -> Unit) {
        MainScope().launch {
            connectivityStatus.isNetworkConnected.collect { status ->
                success(status)
            }
        }
    }
}
