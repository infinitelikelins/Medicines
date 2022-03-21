package com.infinite.pay.medicines.data.entity

/**
 * 处方
 */
data class Prescription(
    val id: Int,
    val serial: String,
    val time: Long,
    val medicines: MutableList<Goods>
)
