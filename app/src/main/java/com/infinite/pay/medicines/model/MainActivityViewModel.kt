package com.infinite.pay.medicines.model

import android.view.View
import android.view.Window
import androidx.lifecycle.ViewModel
import androidx.metrics.performance.JankStats
import androidx.metrics.performance.PerformanceMetricsState
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

class MainActivityViewModel : ViewModel() {

    private lateinit var stats: JankStats
    private lateinit var holder: PerformanceMetricsState.MetricsStateHolder

    fun initStats(root: View, window: Window) {
        holder = PerformanceMetricsState.getForHierarchy(root)
        stats = JankStats.createAndTrack(window, Dispatchers.Default.asExecutor()) {
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