package uz.gita.waterdrink.dialogs

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrink.R
import uz.gita.waterdrink.databinding.DialogGenderChangeBinding
import uz.gita.waterdrink.sharedPref.SharedPref

class ChangeGender : DialogFragment(R.layout.dialog_gender_change) {
    private val binding by viewBinding(DialogGenderChangeBinding::bind)
    val pref = SharedPref.getShared()
    private var text = pref.gender

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }
    private var genderListener: ((String) -> Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        if( pref.gender == "Male" ){
            binding.girl.setBackgroundResource(R.drawable.white_background)
            binding.boy.setBackgroundResource(R.drawable.state_background)}
        else{
            binding.girl.setBackgroundResource(R.drawable.state_background)
            binding.boy.setBackgroundResource(R.drawable.white_background)}
        binding.boy.setOnClickListener {
            pref.gender = "Male"
            text = "Male"
            it.setBackgroundResource(R.drawable.state_background)
            binding.girl.setBackgroundResource(R.drawable.white_background)
        }

        binding.girl.setOnClickListener {
            pref.gender = "Female"
            it.setBackgroundResource(R.drawable.state_background)
            binding.boy.setBackgroundResource(R.drawable.white_background)
            text = "Female"
        }


        binding.okGenderBtn.setOnClickListener {
            genderListener?.invoke("$text")
            pref.gender = text
            dismiss()
        }
        binding.cancelGender.setOnClickListener {
            dismiss()
        }
    }

    fun setGenderListener(f: (String) -> Unit) {
        genderListener = f
    }
}