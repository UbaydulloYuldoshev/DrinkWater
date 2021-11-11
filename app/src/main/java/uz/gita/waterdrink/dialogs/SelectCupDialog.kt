package uz.gita.waterdrink.dialogs

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.waterdrink.R
import uz.gita.waterdrink.databinding.DialogChangeCupBinding
import uz.gita.waterdrink.sharedPref.SharedPref

class SelectCupDialog : DialogFragment(R.layout.dialog_change_cup) {
    private val binding by viewBinding(DialogChangeCupBinding::bind)

    private var listener :(()->Unit)? = null

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

        if (pref.cupSize == 100)
            binding.size100.setBackgroundResource(R.drawable.state_background)
        else
            check(pref.cupSize)
        binding.size100.setOnClickListener {
            pref.cupSize = 100
            check(100)
            listener?.invoke()
        }
        binding.size150.setOnClickListener {
            pref.cupSize = 150
            check(150)
            listener?.invoke()

        }
        binding.size200.setOnClickListener {
            pref.cupSize = 200
            check(200)
            listener?.invoke()
        }
        binding.size250.setOnClickListener {
            pref.cupSize = 250
            check(250)
            listener?.invoke()
        }
        binding.size300.setOnClickListener {
            pref.cupSize = 300
            check(300)
            listener?.invoke()

        }
        binding.size400.setOnClickListener {
            pref.cupSize = 400
            check(400)
            listener?.invoke()
        }
        binding.size500.setOnClickListener {
            pref.cupSize = 500
            check(500)
            listener?.invoke()
        }

        binding.btnOk.setOnClickListener {
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

    }

    private fun check(size: Int) {
        when (size) {
            100 -> {
                binding.size100.setBackgroundResource(R.drawable.state_background)
                binding.size150.setBackgroundResource(R.drawable.white_background)
                binding.size200.setBackgroundResource(R.drawable.white_background)
                binding.size250.setBackgroundResource(R.drawable.white_background)
                binding.size300.setBackgroundResource(R.drawable.white_background)
                binding.size400.setBackgroundResource(R.drawable.white_background)
                binding.size500.setBackgroundResource(R.drawable.white_background)
            }
            150 -> {
                binding.size100.setBackgroundResource(R.drawable.white_background)
                binding.size150.setBackgroundResource(R.drawable.state_background)
                binding.size200.setBackgroundResource(R.drawable.white_background)
                binding.size250.setBackgroundResource(R.drawable.white_background)
                binding.size300.setBackgroundResource(R.drawable.white_background)
                binding.size400.setBackgroundResource(R.drawable.white_background)
                binding.size500.setBackgroundResource(R.drawable.white_background)
            }
            200 -> {
                binding.size100.setBackgroundResource(R.drawable.white_background)
                binding.size150.setBackgroundResource(R.drawable.white_background)
                binding.size200.setBackgroundResource(R.drawable.state_background)
                binding.size250.setBackgroundResource(R.drawable.white_background)
                binding.size300.setBackgroundResource(R.drawable.white_background)
                binding.size400.setBackgroundResource(R.drawable.white_background)
                binding.size500.setBackgroundResource(R.drawable.white_background)
            }
            250 -> {
                binding.size100.setBackgroundResource(R.drawable.white_background)
                binding.size150.setBackgroundResource(R.drawable.white_background)
                binding.size200.setBackgroundResource(R.drawable.white_background)
                binding.size250.setBackgroundResource(R.drawable.state_background)
                binding.size300.setBackgroundResource(R.drawable.white_background)
                binding.size400.setBackgroundResource(R.drawable.white_background)
                binding.size500.setBackgroundResource(R.drawable.white_background)
            }
            300 -> {
                binding.size100.setBackgroundResource(R.drawable.white_background)
                binding.size150.setBackgroundResource(R.drawable.white_background)
                binding.size200.setBackgroundResource(R.drawable.white_background)
                binding.size250.setBackgroundResource(R.drawable.white_background)
                binding.size300.setBackgroundResource(R.drawable.state_background)
                binding.size400.setBackgroundResource(R.drawable.white_background)
                binding.size500.setBackgroundResource(R.drawable.white_background)
            }
            400 -> {
                binding.size100.setBackgroundResource(R.drawable.white_background)
                binding.size150.setBackgroundResource(R.drawable.white_background)
                binding.size200.setBackgroundResource(R.drawable.white_background)
                binding.size250.setBackgroundResource(R.drawable.white_background)
                binding.size300.setBackgroundResource(R.drawable.white_background)
                binding.size400.setBackgroundResource(R.drawable.state_background)
                binding.size500.setBackgroundResource(R.drawable.white_background)
            }
            500 -> {
                binding.size100.setBackgroundResource(R.drawable.white_background)
                binding.size150.setBackgroundResource(R.drawable.white_background)
                binding.size200.setBackgroundResource(R.drawable.white_background)
                binding.size250.setBackgroundResource(R.drawable.white_background)
                binding.size300.setBackgroundResource(R.drawable.white_background)
                binding.size400.setBackgroundResource(R.drawable.white_background)
                binding.size500.setBackgroundResource(R.drawable.state_background)
            }
        }

    }
    fun setListener(f:()->Unit){
        listener = f
    }
}