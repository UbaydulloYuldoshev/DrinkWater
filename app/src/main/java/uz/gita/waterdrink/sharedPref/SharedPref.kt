package uz.gita.waterdrink.sharedPref

import android.content.Context
import uz.gita.waterdrink.app.App

class SharedPref private constructor() {
    private val pref = App.instance.getSharedPreferences("WaterDrink", Context.MODE_PRIVATE)

    companion object {
        private lateinit var instance: SharedPref

        fun getShared(): SharedPref {
            if (!::instance.isInitialized) {
                instance = SharedPref()
            }
            return instance
        }
    }

    var isNew: Int
        get() = pref.getInt("isNew", 0)
        set(value) = pref.edit().putInt("isNew", value).apply()

    var waterAmount: Int
        get() = pref.getInt("amount", 0)
        set(value) = pref.edit().putInt("amount", value).apply()

    var gender: String?
        get() = pref.getString("gender", "Male")
        set(value) = pref.edit().putString("gender", value).apply()

    var weight: String?
        get() = pref.getString("weight", "--")
        set(value) = pref.edit().putString("weight", value).apply()

    var wakeUpTimeHour: String?
        get() = pref.getString("wakeUpTimeHour", "--")
        set(value) = pref.edit().putString("wakeUpTimeHour", value).apply()

    var wakeUpTimeMinute: String?
        get() = pref.getString("wakeUpTimeMinute", "--")
        set(value) = pref.edit().putString("wakeUpTimeMinute", value).apply()

    var bedTimeHour: String?
        get() = pref.getString("bedTimeHour", "--")
        set(value) = pref.edit().putString("bedTimeHour", value).apply()

    var bedTimeMinute: String?
        get() = pref.getString("bedTimeMinute", "--")
        set(value) = pref.edit().putString("bedTimeMinute", value).apply()

    var cupSize: Int
        get() = pref.getInt("cupSize", 100)
        set(value) = pref.edit().putInt("cupSize", value).apply()

    var notifyTime: Long
        get() = pref.getLong("time", 3600000)
        set(value) = pref.edit().putLong("time", value).apply()

    var targetWater: String?
        get() = pref.getString("targetWater", "2210")
        set(value) = pref.edit().putString("targetWater", value).apply()

    var stateGender: Int
        get() = pref.getInt("genderState", 0)
        set(value) = pref.edit().putInt("genderState", value).apply()
}