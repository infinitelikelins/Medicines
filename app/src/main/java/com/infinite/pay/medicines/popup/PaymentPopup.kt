package com.infinite.pay.medicines.popup

import android.content.Context
import android.view.View
import com.infinite.pay.medicines.R
import com.infinite.pay.medicines.databinding.PopupPaymentBinding

class PaymentPopup(context: Context, private val totalPay: Double = 0.0) : AbsBasePopup(context) {

    private lateinit var bindView: PopupPaymentBinding
    var popupWithCompleted: (() -> Unit)? = null

    init {
        setContentView(R.layout.popup_payment)
    }

    override fun onViewCreated(contentView: View) {
        bindView = PopupPaymentBinding.bind(contentView)
        bindView.paymentTotal.text = "$totalPay"
        withClick(R.id.pay_completed) {
            popupWithCompleted?.invoke()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindView.unbind()
    }

}