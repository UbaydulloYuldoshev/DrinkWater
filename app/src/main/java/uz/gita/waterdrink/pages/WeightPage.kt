package uz.gita.waterdrink.pages

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrink.R
import uz.gita.waterdrink.databinding.PageWeightIndicatorBinding
import uz.gita.waterdrink.noteviewmodel.SharedViewModel
import uz.gita.waterdrink.sharedPref.SharedPref

class WeightPage : Fragment(R.layout.page_weight_indicator) {
    private val binding by viewBinding(PageWeightIndicatorBinding::bind)
    val pref = SharedPref.getShared()
    private val  sharedVM: SharedViewModel by activityViewModels<SharedViewModel>()


    private var listener :((String, ImageView,ImageView)->Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TTTT","OnViewCreatedWeight")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        binding.numberPicker.minValue = 5
        binding.numberPicker.maxValue = 200
        if( pref.gender == "Male"){
            binding.personBoy.visibility = View.VISIBLE
            binding.personGirl.visibility = View.INVISIBLE
        }
        else if( pref.gender == "Female" ){
            binding.personBoy.visibility = View.INVISIBLE
            binding.personGirl.visibility = View.VISIBLE
        }
        binding.numberPicker.wrapSelectorWheel = true
        binding.numberPicker.setOnValueChangedListener { numberPicker, old, new ->
            pref.weight = new.toString()
            listener?.invoke(new.toString(),binding.personBoy,binding.personGirl)
        }
        sharedVM.changeImageLiveData.observe(viewLifecycleOwner,{
            Log.d("RRRR","sharedWP")
            changeImage()
        })
    }

    fun setListener (f: (String,ImageView,ImageView)->Unit){
        listener = f
    }

   private fun changeImage(){
        if( pref.gender == "Male"){
            binding.personBoy.visibility = View.VISIBLE
            binding.personGirl.visibility = View.INVISIBLE
        }
        else if( pref.gender == "Female" ){
            binding.personBoy.visibility = View.INVISIBLE
            binding.personGirl.visibility = View.VISIBLE
        }
    }

}