package com.infinite.pay.medicines

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class MainApp : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

}