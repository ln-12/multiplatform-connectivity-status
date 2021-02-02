package com.github.ln12.library

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow

actual class ConnectivityStatus(private val context: Context) {
    actual val isNetworkConnected = MutableStateFlow(false)

    private val connectivityManager: ConnectivityManager? = null
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            Log.d("Connectivity status", "Connected")
            isNetworkConnected.value = true
        }

        override fun onLost(network: Network) {
            Log.d("Connectivity status", "Disconnected")
            isNetworkConnected.value = false
        }
    }

    actual fun start() {
        try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } catch (e: Exception) {
            Log.d("Connectivity status", "Failed to start: ${e.message.toString()}")
            e.printStackTrace()
            isNetworkConnected.value = false
        }
    }

    actual fun stop() {
        connectivityManager?.unregisterNetworkCallback(networkCallback)
    }
}