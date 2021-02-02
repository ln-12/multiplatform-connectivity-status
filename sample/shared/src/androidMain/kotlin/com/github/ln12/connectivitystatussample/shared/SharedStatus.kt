package com.github.ln12.connectivitystatussample.shared


import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import com.github.ln12.library.ConnectivityStatus
import kotlinx.coroutines.flow.MutableStateFlow

actual class SharedStatus(context: Context) {
    private val connectivityStatus = ConnectivityStatus(context)
    actual val current = connectivityStatus.isNetworkConnected

    actual fun start() {
        connectivityStatus.start()
    }

    actual fun stop() {
        connectivityStatus.stop()
    }
}