package com.github.ln12.connectivitystatussample.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.github.ln12.connectivitystatussample.shared.SharedStatus
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var connectivityStatus: SharedStatus? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Connectivity status"
        setContentView(R.layout.activity_main)

        connectivityStatus = SharedStatus(this)

        val tv: TextView = findViewById(R.id.text_view)
        MainScope().launch {
            connectivityStatus?.current?.collect {
                tv.text = "Status: $it"
            }
        }
    }

    override fun onResume() {
        super.onResume()
        connectivityStatus?.start()
    }

    override fun onPause() {
        super.onPause()
        connectivityStatus?.stop()
    }
}
