package uz.gita.waterdrink.screens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrink.R
import uz.gita.waterdrink.databinding.ScreenLastToStartBinding
import uz.gita.waterdrink.sharedPref.SharedPref

class LastToStartScreen : Fragment(R.layout.screen_last_to_start) {
    private val viewBinding by viewBinding (ScreenLastToStartBinding::bind)
    val pref = SharedPref.getShared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val handler = Handler(Looper.getMainLooper())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        handler.postDelayed({
            pref.isNew = 1
            findNavController().navigate(R.id.action_lastToStartScreen_to_mainScreen)
        }, 5000)

    }
}
