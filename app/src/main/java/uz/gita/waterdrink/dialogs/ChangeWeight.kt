package uz.gita.waterdrink.dialogs

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrink.R
import uz.gita.waterdrink.databinding.DialogTargetChangeBinding
import uz.gita.waterdrink.databinding.DialogWeightChangeBinding
import uz.gita.waterdrink.sharedPref.SharedPref

class ChangeWeight : DialogFragment(R.layout.dialog_weight_change) {
    private val binding by viewBinding(DialogWeightChangeBinding::bind)

    private var listener: ((String) -> Unit)? = null

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = SharedPref.getShared()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding.numberPicker.value = pref.weight!!.toInt()
        var weight = 0
        binding.numberPicker.minValue = 5
        binding.numberPicker.maxValue = 200
        binding.numberPicker.wrapSelectorWheel = true
        binding.numberPicker.setOnValueChangedListener { _, _, new ->
            weight = new
            listener?.invoke(new.toString())
        }
        binding.okWeightBtn.setOnClickListener {
            pref.weight = weight.toString()
            dismiss()
        }
        binding.cancelWeight.setOnClickListener {
            dismiss()
        }
    }

    fun setListener(f: (String) -> Unit) {
        listener = f
    }

}
