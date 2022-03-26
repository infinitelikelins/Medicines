package com.infinite.pay.medicines.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import com.infinite.pay.medicines.R
import com.infinite.pay.medicines.databinding.ActivityMainBinding
import com.orhanobut.logger.Logger

class MainActivity : AppCompatActivity() {

    private lateinit var bindView: ActivityMainBinding

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
    }

    override fun onSupportNavigateUp(): Boolean =
        Navigation.findNavController(this, R.id.container_fragment).navigateUp()

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallback)
    }

}