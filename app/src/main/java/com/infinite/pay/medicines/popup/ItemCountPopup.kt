package com.infinite.pay.medicines.popup

import android.content.Context
import android.view.View
import com.infinite.pay.medicines.R
import com.infinite.pay.medicines.adapter.MedicineCountAdapter
import com.infinite.pay.medicines.databinding.PopupItemCountBinding

class ItemCountPopup(context: Context) : AbsBasePopup(context) {

    private lateinit var bindView: PopupItemCountBinding
    var popupWithClick: ((itemCount: Int) -> Unit)? = null

    init {
        setContentView(R.layout.popup_item_count)
    }

    override fun onViewCreated(contentView: View) {
        bindView = PopupItemCountBinding.bind(contentView)
        bindView.medicineCountList.adapter =
            MedicineCountAdapter(150).apply {
                setOnItemClickListener { _, _, position ->
                    popupWithClick?.invoke(position + 1)
                    dismiss(true)
                }
            }
        withClick(R.id.close, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        bindView.unbind()
    }

}