package com.infinite.pay.medicines.data.entity

data class Goods(
    val medicine: Medicine,
    var count: Int = 1
) {

    fun total() = medicine.sale * count

}