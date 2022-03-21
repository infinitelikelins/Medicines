package com.infinite.pay.medicines.enums

import androidx.room.TypeConverter

/**
 * 计量单位
 */
enum class UnitEnum(val unitName: String) {
    GRAIN("粒"),
    PACKET("包"),
    BOX("盒"),
    GRAM("克"),
    BOTTLE("瓶"),
    NUMBER("个");
}

class UnitConverter {
    @TypeConverter
    fun fromUnit(units: UnitEnum): String = units.name

    @TypeConverter
    fun toUnit(unitName: String): UnitEnum = UnitEnum.valueOf(unitName)
}


