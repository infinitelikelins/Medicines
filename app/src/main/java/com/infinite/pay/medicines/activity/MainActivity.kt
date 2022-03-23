package com.infinite.pay.medicines.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.metrics.performance.JankStats
import androidx.metrics.performance.PerformanceMetricsState
import androidx.navigation.Navigation
import com.infinite.pay.medicines.R
import com.infinite.pay.medicines.databinding.ActivityMainBinding
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

class MainActivity : AppCompatActivity() {

    private lateinit var bindView: ActivityMainBinding
    private lateinit var jankStats: JankStats
    private lateinit var holder: PerformanceMetricsState.MetricsStateHolder

    private val fragmentLifecycleCallback = object : FragmentManager.FragmentLifecycleCallbacks() {

        override fun onFragmentCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            if ("SupportRequestManagerFragment" != f.javaClass.simpleName) {
                Logger.t("Current").v("===== ${f.javaClass.simpleName} onFragmentCreated ===== ")
            }
        }

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
            if ("SupportRequestManagerFragment" != f.javaClass.simpleName) {
                Logger.t("Current").v("===== ${f.javaClass.simpleName} onFragmentDestroyed ===== ")
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallback, true)

        holder = PerformanceMetricsState.getForHierarchy(bindView.root)
        jankStats = JankStats.createAndTrack(window, Dispatchers.Default.asExecutor()) {
            Logger.t("jankStats").d("${it.isJank} -- ${it.frameDurationUiNanos/1000000000.0}s -- ${it.states}")
        }
        jankStats.jankHeuristicMultiplier = 10.0f
        holder.state?.addState("MainActivity", "onCreate")

    }

    override fun onSupportNavigateUp(): Boolean =
        Navigation.findNavController(this, R.id.container_fragment).navigateUp()

    override fun onResume() {
        super.onResume()
        holder.state?.addState("MainActivity", "onResume")
        jankStats.isTrackingEnabled = true
    }

    override fun onPause() {
        super.onPause()
        holder.state?.removeState("MainActivity")
        jankStats.isTrackingEnabled = false
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallback)
    }

}