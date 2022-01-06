package com.github.`ln-12`.library

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
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

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // API 24 and above
                connectivityManager.registerDefaultNetworkCallback(networkCallback)

                val currentNetwork = connectivityManager.activeNetwork

                if(currentNetwork == null) {
                    isNetworkConnected.value = false

                    Log.d("Connectivity status", "Disconnected")
                }
            } else {
                // API 23 and below
                val networkRequest = NetworkRequest.Builder().apply {
                    addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    }
                }.build()

                connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

                val currentNetwork = connectivityManager.activeNetworkInfo

                if(currentNetwork == null || (
                    currentNetwork.type != ConnectivityManager.TYPE_ETHERNET &&
                    currentNetwork.type != ConnectivityManager.TYPE_WIFI &&
                    currentNetwork.type != ConnectivityManager.TYPE_MOBILE
                )) {
                    isNetworkConnected.value = false

                    Log.d("Connectivity status", "Disconnected")
                }
            }

            Log.d("Connectivity status", "Started")
        } catch (e: Exception) {
            Log.d("Connectivity status", "Failed to start: ${e.message.toString()}")
            e.printStackTrace()
            isNetworkConnected.value = false
        }
    }

    actual fun stop() {
        connectivityManager?.unregisterNetworkCallback(networkCallback)
        Log.d("Connectivity status", "Stopped")
    }
}