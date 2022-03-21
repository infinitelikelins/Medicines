package com.infinite.pay.medicines.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.infinite.pay.medicines.R

class MedicineCountAdapter(countSize: Int) :
    BaseQuickAdapter<Int, BaseViewHolder>(R.layout.item_medicine_count, MutableList(countSize) { it + 1 }) {

    override fun convert(holder: BaseViewHolder, item: Int) {
        holder.setText(R.id.medicine_count, "$item")
    }

}