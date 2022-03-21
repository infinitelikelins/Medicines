package com.infinite.pay.medicines.adapter

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.infinite.pay.medicines.R
import com.infinite.pay.medicines.data.entity.Goods

/**
 * 处方添加列表
 */
class PrescriptionItemAdapter : BaseQuickAdapter<Goods, BaseViewHolder>(R.layout.item_prescription) {

    init {
        setDiffCallback(object : DiffUtil.ItemCallback<Goods>() {
            override fun areItemsTheSame(oldItem: Goods, newItem: Goods): Boolean = oldItem.medicine.id == newItem.medicine.id
            override fun areContentsTheSame(oldItem: Goods, newItem: Goods): Boolean = oldItem == newItem
        })
    }

    override fun convert(holder: BaseViewHolder, item: Goods) {
        holder.setText(R.id.medicine, "${item.medicine.id}.${item.medicine.name}")
        holder.setText(R.id.count,"${item.count}")
    }

}