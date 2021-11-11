package uz.gita.waterdrink.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrink.R
import uz.gita.waterdrink.databinding.ScreenBeginningBinding

class BeginningScreen :Fragment(R.layout.screen_beginning) {
    private val viewBinding by viewBinding(ScreenBeginningBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        viewBinding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_beginningScreen_to_intoScreen)
        }

    }
}