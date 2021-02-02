package com.github.ln12.connectivitystatussample.shared

import kotlinx.coroutines.flow.MutableStateFlow

expect class SharedStatus {
    val current: MutableStateFlow<Boolean>
    fun start()
    fun stop()
}