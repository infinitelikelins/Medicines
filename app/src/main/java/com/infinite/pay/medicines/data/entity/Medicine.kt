package com.infinite.pay.medicines.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.infinite.pay.medicines.enums.SaleModeEnum
import com.infinite.pay.medicines.enums.UnitEnum

/**
 * 药品商品
 */
@Entity(tableName = "medicine")
data class Medicine(
    @PrimaryKey(autoGenerate = true) val id: Int, // id
    var name: String, // 名称
    var alias: String? = null, // 别名
    var searching: String? = null, // 检索
    var category: String? = null, // 类别
    var manufacturer: String? = null,  // 厂家
    var barcode: String? = null, // 条形码
    var sale: Double = 10.0, // 售价
    var units: UnitEnum = UnitEnum.GRAIN, // 计量单位
    var mode: SaleModeEnum = SaleModeEnum.RETAIL, // 模式
)

