package com.infinite.pay.medicines.enums

import androidx.room.TypeConverter

enum class SaleModeEnum(val modeName :String) {

    RETAIL("零售"),
    PRESCRIPTION("处方");

}

class SaleModeConverter {
    @TypeConverter
    fun fromSaleModeEnum(mode: SaleModeEnum): String = mode.name

    @TypeConverter
    fun toSaleModeEnum(modeName: String): SaleModeEnum = SaleModeEnum.valueOf(modeName)
}
