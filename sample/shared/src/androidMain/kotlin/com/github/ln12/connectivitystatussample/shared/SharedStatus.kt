package com.github.ln12.connectivitystatussample.shared


import android.content.Context
import com.github.ln_12.library.ConnectivityStatus

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