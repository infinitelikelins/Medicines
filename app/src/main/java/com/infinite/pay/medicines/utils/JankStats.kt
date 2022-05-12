package com.infinite.pay.medicines.utils

import android.view.View
import android.view.Window
import androidx.metrics.performance.PerformanceMetricsState
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

class Stats private constructor(root: View, window: Window) {

    companion object {
        fun newInstance(root: View, window: Window) = Stats(root, window)
    }

    private val stats: androidx.metrics.performance.JankStats
    private val holder: PerformanceMetricsState.MetricsStateHolder

    init {
        holder = PerformanceMetricsState.getForHierarchy(root)
        stats = androidx.metrics.performance.JankStats.createAndTrack(
            window,
            Dispatchers.Default.asExecutor()
        ) {
            if (it.isJank) {
                Logger.t("JankStats")
                    .d("${it.states} -- ${it.frameDurationUiNanos / 1000000000.0}s")
            }
        }
        stats.jankHeuristicMultiplier = 5.0f
        stats.isTrackingEnabled = true
    }

    fun addStats(statsName: String, stats: String) {
        holder.state?.addState(statsName, stats)
    }

    fun removeStats(statsName: String) {
        holder.state?.removeState(statsName)
    }

}