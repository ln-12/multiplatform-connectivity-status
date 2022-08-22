package com.github.ln12.connectivitystatussample.shared

import com.github.ln_12.library.ConnectivityStatus
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

actual class SharedStatus {
    private val connectivityStatus: ConnectivityStatus = ConnectivityStatus()
    actual val current: MutableStateFlow<Boolean> = connectivityStatus.isNetworkConnected

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
