package uz.gita.waterdrink.pages

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrink.R
import uz.gita.waterdrink.databinding.PageBedTimeBinding
import uz.gita.waterdrink.noteviewmodel.SharedViewModel
import uz.gita.waterdrink.sharedPref.SharedPref

class BedTimePage : Fragment(R.layout.page_bed_time) {
    private val binding by viewBinding(PageBedTimeBinding::bind)
    private var listener: ((String) -> Unit)? = null
    private val sharedVM: SharedViewModel by activityViewModels<SharedViewModel>()
    val pref = SharedPref.getShared()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TTTT", "OnViewCreatedBed")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        if (pref.gender == "Male") {
            binding.personBoy.visibility = View.VISIBLE
            binding.personGirl.visibility = View.INVISIBLE
        } else if (pref.gender == "Female") {
            binding.personBoy.visibility = View.INVISIBLE
            binding.personGirl.visibility = View.VISIBLE
        }
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
            value = 23
            displayedValues = hours.toTypedArray()
            setOnValueChangedListener(NumberPicker.OnValueChangeListener { _, _, newVal ->
                hour = hours[newVal]
                pref.bedTimeHour = hour
                listener?.invoke("${pref.bedTimeHour}:${pref.bedTimeMinute}")


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
                pref.bedTimeMinute = minute
                listener?.invoke("${pref.bedTimeHour}:${pref.bedTimeMinute}")
            })
        }

        sharedVM.changeImageLiveData.observe(viewLifecycleOwner, {
            changeImage()
        })
    }

    fun setListener(f: (String) -> Unit) {
        listener = f
    }

    private fun changeImage() {
        if (pref.gender == "Male") {
            binding.personBoy.visibility = View.VISIBLE
            binding.personGirl.visibility = View.INVISIBLE
        } else if (pref.gender == "Female") {
            binding.personBoy.visibility = View.INVISIBLE
            binding.personGirl.visibility = View.VISIBLE
        }
    }

}