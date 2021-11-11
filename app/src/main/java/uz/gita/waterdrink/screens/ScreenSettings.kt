package uz.gita.waterdrink.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrink.R
import uz.gita.waterdrink.WorkManagerToDo
import uz.gita.waterdrink.databinding.ScreenSettingsBinding
import uz.gita.waterdrink.dialogs.*
import uz.gita.waterdrink.sharedPref.SharedPref
import uz.gita.waterdrink.utils.scope
import java.util.*
import java.util.concurrent.TimeUnit

class ScreenSettings : Fragment(R.layout.screen_settings) {

    private val viewBinding by viewBinding(ScreenSettingsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.scope {
        super.onViewCreated(view, savedInstanceState)
        val pref = SharedPref.getShared()
        var hour = ""
        var minute = ""
        var hourSleep = ""
        var minuteSleep = ""
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        backToMain.setOnClickListener {
            findNavController().popBackStack()
        }
        viewBinding.settingWaterTarget.text = "${pref.targetWater} ml"
        viewBinding.weightText.text = pref.weight
        viewBinding.wakeUpTimeText.text = "${pref.wakeUpTimeHour} : ${pref.wakeUpTimeMinute}"
        viewBinding.sleepingTimeText.text = "${pref.bedTimeHour} : ${pref.bedTimeMinute}"
        viewBinding.genderText.text = pref.gender

        setTarget.setOnClickListener {
            val dialog = DialogChangeTarget()
            dialog.setTargetListener {
                viewBinding.settingWaterTarget.text = pref.targetWater
            }
            dialog.show(childFragmentManager, "dialog")
        }
        changeGender.setOnClickListener {
            val dialog = ChangeGender()
            dialog.setGenderListener {
                viewBinding.genderText.text = pref.gender
            }
            dialog.show(childFragmentManager, "dialog")
        }

        changeWeight.setOnClickListener {
            val dialog = ChangeWeight()
            dialog.setListener {
                viewBinding.weightText.text = it
                pref.weight = it
            }
            dialog.show(childFragmentManager, "dialog")
        }
        changeWakeUpTime.setOnClickListener {
            val dialog = ChangeWakeUpTime()
            dialog.setSleepHourTimeListener {
                hourSleep = it
                pref.wakeUpTimeHour = hourSleep
                viewBinding.wakeUpTimeText.text = "$hourSleep : $minuteSleep"
            }
            dialog.setSleepMinuteTimeListener {
                minuteSleep = it
                pref.wakeUpTimeMinute = minuteSleep
                viewBinding.wakeUpTimeText.text = "$hourSleep : $minuteSleep"
            }
            dialog.show(childFragmentManager, "dialog")
        }
        changeSleepingTime.setOnClickListener {
            val dialog = ChangeSleepTime()
            dialog.setSleepHourTimeListener {
                hour = it
                pref.bedTimeHour = hour
                viewBinding.sleepingTimeText.text = "$hour : $minute"
            }
            dialog.setSleepMinuteTimeListener {
                minute = it
                viewBinding.sleepingTimeText.text = "$hour : $minute"
                pref.bedTimeMinute = minute
            }
            dialog.show(childFragmentManager, "dialog")
        }
        viewBinding.setReminder.setOnClickListener {
            val dialog = DialogReminder()
            dialog.setGenderListener {
                val data = Data.Builder()
                data.putInt("id", 0)
                data.putString("title", "Water Reminder")
                data.putString("description", "It is time to drink water")
                val uploadWorkerRequest: WorkRequest =
                    OneTimeWorkRequest.Builder(WorkManagerToDo::class.java)
                        .setInitialDelay(pref.notifyTime, TimeUnit.MILLISECONDS)
                        .setInputData(data.build()).build()
                WorkManager.getInstance(requireContext()).enqueue(uploadWorkerRequest)
                pref.notifyTime = it
            }
            dialog.show(childFragmentManager, "dialog")
        }
        viewBinding.share.setOnClickListener {
            try {
                val i = Intent(Intent.ACTION_SEND)
                i.type = "text/plain"
                i.putExtra(Intent.EXTRA_SUBJECT, "Your Subject")
                var sAux = "\nLet me recommend you this application\n\n"
                sAux = """
                ${sAux}https://play.google.com/store/apps/details?id=uz.gita.waterdrink""".trimIndent() // here define package name of you app
                i.putExtra(Intent.EXTRA_TEXT, sAux)
                startActivity(Intent.createChooser(i, "choose one"))
            } catch (e: Exception) {
                Log.e(">>>", "Error: $e")
            }
        }
    }
}
/**
val data = Data.Builder()
data.putInt("id", 0)
data.putString("title", "${viewBinding.addNoteTitle.editText?.text}")
data.putString("description", "${viewBinding.addNoteDescription.editText?.text}")
val uploadWorkerRequest: WorkRequest =
OneTimeWorkRequest.Builder(WorkManagerToDo::class.java)
.setInitialDelay(calendar2.timeInMillis-Calendar.getInstance().timeInMillis, TimeUnit.MILLISECONDS)
.setInputData(data.build()).build()
WorkManager.getInstance(requireContext()).enqueue(uploadWorkerRequest)
 **/