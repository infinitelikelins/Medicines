package com.infinite.pay.medicines.popup

import android.content.Context
import android.view.View
import android.view.animation.Animation
import androidx.annotation.IdRes
import razerdp.basepopup.BasePopupWindow
import razerdp.util.animation.AlphaConfig
import razerdp.util.animation.AnimationHelper
import razerdp.util.animation.ScaleConfig

abstract class AbsBasePopup(context: Context?) : BasePopupWindow(context) {

    override fun onCreateShowAnimation(): Animation =
        AnimationHelper.asAnimation()
            .withScale(ScaleConfig.CENTER.duration(300L))
            .withAlpha(AlphaConfig.IN.duration(300L))
            .toShow()

    override fun onCreateDismissAnimation(): Animation =
        AnimationHelper.asAnimation()
            .withScale(ScaleConfig.CENTER.duration(300L))
            .withAlpha(AlphaConfig.OUT.duration(300L))
            .toDismiss()

    protected fun withClick(@IdRes id: Int, onClickListener: View.OnClickListener? , dismissEnable : Boolean = true) {
        findViewById<View>(id)?.setOnClickListener { v: View? ->
            onClickListener?.onClick(v)
            if (dismissEnable) dismiss(true)
        }
    }

}