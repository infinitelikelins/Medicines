package com.infinite.pay.medicines.initializer

import android.content.Context
import androidx.startup.Initializer
import com.infinite.pay.medicines.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.tencent.mmkv.MMKV

class AppInit : Initializer<Any> {

    override fun create(context: Context) {
        Logger.addLogAdapter(object : AndroidLogAdapter(
            PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(1)
                .tag("LOGGER")
                .build()
        ) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

        MMKV.initialize(context)

    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()

}