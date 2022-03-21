package com.infinite.pay.medicines

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.infinite.pay.medicines.data.dao.MedicineDao
import com.infinite.pay.medicines.data.entity.Medicine
import com.infinite.pay.medicines.enums.SaleModeConverter
import com.infinite.pay.medicines.enums.UnitConverter

@Database(
    entities = [
        Medicine::class
    ], version = 1, exportSchema = false
)
@TypeConverters(UnitConverter::class, SaleModeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMedicineDao() : MedicineDao

    companion object {
        lateinit var instance: AppDatabase
    }

}