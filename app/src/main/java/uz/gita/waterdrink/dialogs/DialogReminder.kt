package uz.gita.waterdrink.dialogs

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrink.R
import uz.gita.waterdrink.databinding.DialogReminderBinding
import uz.gita.waterdrink.sharedPref.SharedPref

class DialogReminder : DialogFragment(R.layout.dialog_reminder) {
    private val binding by viewBinding(DialogReminderBinding::bind)
    val pref = SharedPref.getShared()
    private var text = pref.gender

    private var genderListener: ((Long) -> Unit)? = null
    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        if (pref.notifyTime.toInt() == 3600000) {
            binding.anHour.setBackgroundResource(R.drawable.state_click)
            binding.neverHour.setBackgroundResource(R.drawable.state_unclick)
            binding.twoHour.setBackgroundResource(R.drawable.state_unclick)
        }
        if (pref.notifyTime.toInt() == 7200000) {
            binding.anHour.setBackgroundResource(R.drawable.state_unclick)
            binding.neverHour.setBackgroundResource(R.drawable.state_unclick)
            binding.twoHour.setBackgroundResource(R.drawable.state_click)
        }
        if (pref.notifyTime.toInt() == 9999999) {
            binding.anHour.setBackgroundResource(R.drawable.state_unclick)
            binding.neverHour.setBackgroundResource(R.drawable.state_click)
            binding.twoHour.setBackgroundResource(R.drawable.state_unclick)
        }

        binding.anHour.setOnClickListener {
            pref.notifyTime = 3600000
            it.setBackgroundResource(R.drawable.state_click)
            binding.neverHour.setBackgroundResource(R.drawable.state_unclick)
            binding.twoHour.setBackgroundResource(R.drawable.state_unclick)
        }

        binding.twoHour.setOnClickListener {
            pref.notifyTime = 7200000
            it.setBackgroundResource(R.drawable.state_click)
            binding.neverHour.setBackgroundResource(R.drawable.state_unclick)
            binding.anHour.setBackgroundResource(R.drawable.state_unclick)
        }
        binding.neverHour.setOnClickListener {
            pref.notifyTime = 9999999
            it.setBackgroundResource(R.drawable.state_click)
            binding.anHour.setBackgroundResource(R.drawable.state_unclick)
            binding.twoHour.setBackgroundResource(R.drawable.state_unclick)
        }


        binding.okWeightBtn.setOnClickListener {
            genderListener?.invoke(pref.notifyTime)
            pref.gender = text
            dismiss()
        }
        binding.cancelWeight.setOnClickListener {
            dismiss()
        }
    }

    fun setGenderListener(f: (Long) -> Unit) {
        genderListener = f
    }
}