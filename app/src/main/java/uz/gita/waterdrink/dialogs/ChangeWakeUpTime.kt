package uz.gita.waterdrink.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.NumberPicker
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrink.R
import uz.gita.waterdrink.databinding.DialogSleepingTimeChangeBinding
import uz.gita.waterdrink.databinding.DialogTargetChangeBinding
import uz.gita.waterdrink.databinding.DialogWakeUpTimeChangeBinding
import uz.gita.waterdrink.sharedPref.SharedPref

class ChangeWakeUpTime: DialogFragment(R.layout.dialog_wake_up_time_change) {
    private val binding by viewBinding(DialogWakeUpTimeChangeBinding::bind)

    private var sleepTimeHourListener :((String)-> Unit)? = null
    private var sleepTimeMinuteListener :((String)-> Unit)? = null
    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        val pref = SharedPref.getShared()


        var hour = "0"

        val hours = ArrayList<String>()
        for (i in 0 until 24) {
            if (i < 10) {
                hours.add("0$i")
            } else {
                hours.add("$i")
            }
        }
        binding.hourPicker.apply {
            minValue = 0
            maxValue = 23
            value = 6
            displayedValues = hours.toTypedArray()
            setOnValueChangedListener(NumberPicker.OnValueChangeListener { _, _, newVal ->
                hour = hours[newVal]
                sleepTimeHourListener?.invoke(hour)

            })
        }
        val minutes = ArrayList<String>()
        for (i in 0 until 60) {
            if (i < 10) {
                minutes.add("0$i")
            } else {
                minutes.add("$i")
            }
        }
        var minute = "0"
        binding.minutePicker.apply {
            minValue = 0
            maxValue = 59
            value = 0
            displayedValues = minutes.toTypedArray()
            setOnValueChangedListener(NumberPicker.OnValueChangeListener { _, _, newVal ->
                minute = minutes[newVal]
                sleepTimeMinuteListener?.invoke(minute)
            })
        }


        binding.okGenderBtn.setOnClickListener {
            pref.wakeUpTimeHour = hour
            pref.wakeUpTimeMinute = minute
            dismiss()
        }
        binding.cancelGender.setOnClickListener {
            dismiss()
        }

    }
    fun setSleepHourTimeListener (f :(String)->Unit){
        sleepTimeHourListener = f
    }
    fun setSleepMinuteTimeListener (f :(String)->Unit){
        sleepTimeMinuteListener = f
    }
}