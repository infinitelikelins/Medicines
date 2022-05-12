package com.infinite.pay.medicines.adapter

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.infinite.pay.medicines.R
import com.infinite.pay.medicines.data.entity.Medicine

/**
 * 库存处方药品列表
 */
class MedicineItemAdapter : BaseQuickAdapter<Medicine, BaseViewHolder>
    (R.layout.item_medicines, MutableList(110) { Medicine(it + 1, "商品名称") }) {

    init {
        setDiffCallback(object : DiffUtil.ItemCallback<Medicine>() {
            override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine): Boolean = false
        })
    }

    override fun convert(holder: BaseViewHolder, item: Medicine) {
        holder.setText(R.id.name, "${item.id}. ${item.name}")
        holder.setText(R.id.manufacturer, item.manufacturer ?: "未知")
        holder.setText(R.id.units, item.units.unitName)
    }

}