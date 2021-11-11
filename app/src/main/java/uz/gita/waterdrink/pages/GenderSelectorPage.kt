package uz.gita.waterdrink.pages

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrink.R
import uz.gita.waterdrink.databinding.PageGenderSelectorBinding
import uz.gita.waterdrink.noteviewmodel.SharedViewModel
import uz.gita.waterdrink.sharedPref.SharedPref

class GenderSelectorPage :Fragment(R.layout.page_gender_selector) {
    private val binding by viewBinding(PageGenderSelectorBinding::bind)
    private var listener :((String)->Unit)? = null
    val pref = SharedPref.getShared()
    private val  sharedVM:SharedViewModel by activityViewModels<SharedViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TTTT","OnViewCreatedGender")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        if( pref.gender == "Male" ){
            binding.girl.setBackgroundResource(R.drawable.white_background)
            binding.boy.setBackgroundResource(R.drawable.state_background)}
        else{
            binding.girl.setBackgroundResource(R.drawable.state_background)
            binding.boy.setBackgroundResource(R.drawable.white_background)}
        binding.boy.setOnClickListener {
            pref.gender = "Male"
            it.setBackgroundResource(R.drawable.state_background)
            binding.girl.setBackgroundResource(R.drawable.white_background)
            listener?.invoke(pref.gender!!)
            sharedVM.changeImage()
        }
        binding.girl.setOnClickListener {
            pref.gender = "Female"
            it.setBackgroundResource(R.drawable.state_background)
            binding.boy.setBackgroundResource(R.drawable.white_background)
            listener?.invoke(pref.gender!!)
            sharedVM.changeImage()
        }
    }
    fun setListener(f:(String)->Unit){
        listener = f
    }

    override fun onResume() {
        super.onResume()
        Log.d("TTTT","OnresumetedGender")

    }

}

