package uz.gita.waterdrink.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class DrinkWaterEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val time : String,
    val size :String,
): Serializable