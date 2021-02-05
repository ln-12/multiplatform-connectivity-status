package com.github.`ln-12`.library

import kotlinx.coroutines.flow.MutableStateFlow
import cocoapods.Reachability.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import platform.Foundation.NSLog
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.native.concurrent.freeze

actual class ConnectivityStatus {
    actual val isNetworkConnected = MutableStateFlow(false)

    private var reachability: Reachability? = null

    // Swift can't directly use a MutableStateFlow, so the status
    // is exposed via a lambda/closure
    fun getStatus(success: (Boolean) -> Unit) {
        MainScope().launch {
            isNetworkConnected.collect { status ->
                success(status)
            }
        }
    }

    actual fun start() {
        dispatch_async(dispatch_get_main_queue()) {
            reachability = Reachability.reachabilityForInternetConnection()

            val reachableCallback = { reach: Reachability? ->
                dispatch_async(dispatch_get_main_queue(), {
                    NSLog("Connected")

                    isNetworkConnected.value = true
                }.freeze())
            }.freeze()
            reachability?.reachableBlock = reachableCallback

            val unreachableCallback = { reach: Reachability? ->
                dispatch_async(dispatch_get_main_queue(), {
                    NSLog("Disconnected")

                    isNetworkConnected.value = false
                }.freeze())
            }.freeze()
            reachability?.unreachableBlock = unreachableCallback

            reachability?.startNotifier()

            dispatch_async(dispatch_get_main_queue(), {
                isNetworkConnected.value = reachability?.isReachable() ?: false

                NSLog("Initial reachability: ${reachability?.isReachable()}")
            }.freeze())
        }
    }

    actual fun stop() {
        reachability?.stopNotifier()
    }
}
