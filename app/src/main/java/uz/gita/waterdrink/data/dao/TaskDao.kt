package uz.gita.waterdrink.data.dao

import androidx.room.*
import androidx.room.Dao
import uz.gita.waterdrink.data.entity.DrinkWaterEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM DrinkWaterEntity")
    fun getAllElements() : List<DrinkWaterEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data : DrinkWaterEntity)

    @Delete
    fun delete(data: DrinkWaterEntity):Int
}