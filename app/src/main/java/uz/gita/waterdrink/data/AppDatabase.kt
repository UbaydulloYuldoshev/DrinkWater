package uz.gita.waterdrink.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.waterdrink.app.App
import uz.gita.waterdrink.data.dao.TaskDao
import uz.gita.waterdrink.data.entity.DrinkWaterEntity

@Database(entities = [DrinkWaterEntity::class],version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTaskDao() : TaskDao

    companion object {
        private lateinit var instance : AppDatabase
        fun getDatabase() :AppDatabase {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(App.instance,AppDatabase::class.java,"water")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}