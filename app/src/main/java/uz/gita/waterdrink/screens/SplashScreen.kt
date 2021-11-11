package uz.gita.waterdrink.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrink.R
import uz.gita.waterdrink.databinding.ScreenSplashBinding
import uz.gita.waterdrink.sharedPref.SharedPref

class SplashScreen : Fragment(R.layout.screen_splash) {

    private val viewBinding by viewBinding(ScreenSplashBinding::bind)
    private val pref = SharedPref.getShared()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (pref.isNew == 0)
            findNavController().navigate(R.id.action_splashScreen_to_beginningScreen)
        else
            findNavController().navigate(R.id.action_splashScreen_to_mainScreen)
    }

}