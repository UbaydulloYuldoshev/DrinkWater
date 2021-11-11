package uz.gita.waterdrink.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.waterdrink.R
import uz.gita.waterdrink.adapter.WaterAdapter
import uz.gita.waterdrink.data.entity.DrinkWaterEntity
import uz.gita.waterdrink.databinding.ScreenMainBinding
import uz.gita.waterdrink.dialogs.SelectCupDialog
import uz.gita.waterdrink.noteviewmodel.NoteViewModel
import uz.gita.waterdrink.sharedPref.SharedPref
import java.util.*
import kotlin.collections.ArrayList

class MainScreen : Fragment(R.layout.screen_main) {

    private val viewBinding by viewBinding(ScreenMainBinding::bind)
    private lateinit var adapter: WaterAdapter

    private val viewModelNote: NoteViewModel by viewModels()
    private val listData = ArrayList<DrinkWaterEntity>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = SharedPref.getShared()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        viewModelNote.loadData()
        viewBinding.currentCupSize.text = "Size: ${pref.cupSize} ml"

        adapter = WaterAdapter(listData)
        viewBinding.listDrunkWater.adapter = adapter
        viewBinding.listDrunkWater.layoutManager = LinearLayoutManager(requireContext())

        viewBinding.circleWave.max = pref.targetWater!!.toInt()
        viewBinding.circleWave.progress = pref.waterAmount

        viewModelNote.noteListLiveData.observe(viewLifecycleOwner, noteListObserver)
        viewModelNote.deleteLiveData.observe(viewLifecycleOwner, deleteObserver)

        viewBinding.mainAddWaterBtn.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            listData.add(DrinkWaterEntity(0, "$hour:$minute", "${pref.cupSize}"))
            viewModelNote.addNote(DrinkWaterEntity(0, "$hour:$minute", "${pref.cupSize}"))

            viewBinding.circleWave.progress = viewBinding.circleWave.progress + pref.cupSize
            pref.waterAmount = pref.waterAmount + pref.cupSize
            viewBinding.goalWater.text = "${pref.waterAmount} ml"
            adapter.notifyDataSetChanged()
            viewBinding.mainAddWaterBtn.isClickable = false
            lifecycleScope.launch {
                delay(5000)
                viewBinding.mainAddWaterBtn.isClickable = true
            }

        }


        viewBinding.setting.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_screenSettings2)
        }
        viewBinding.goalWater.text = "${pref.waterAmount} ml"

        viewBinding.changeCup.setOnClickListener {
            val dialog = SelectCupDialog()
            dialog.setListener {
                viewBinding.currentCupSize.text = "Size: ${pref.cupSize} ml"
            }
            dialog.show(childFragmentManager, "dialog")
            viewBinding.currentCupSize.text = "Size: ${pref.cupSize} ml"
        }

        viewBinding.targetWater.text = "${pref.targetWater} ml"

        adapter.setListener { data, pos, more ->
//            val popUp = PopupMenu(requireContext(), more)
//            popUp.setOnMenuItemClickListener {
//                if (it.itemId == R.id.delete) {

            if (pref.waterAmount > pref.targetWater.toString().toInt())
                pref.waterAmount = pref.waterAmount - data.size.toInt()
            else {
                pref.waterAmount = pref.waterAmount - data.size.toInt()
                viewBinding.circleWave.progress =
                    viewBinding.circleWave.progress - data.size.toInt()
            }

            viewBinding.goalWater.text = "${pref.waterAmount} ml"
            viewBinding.circleWave.progress = pref.waterAmount
            viewModelNote.delete(data, pos)

        }
//                true
//            }
//            popUp.inflate(R.menu.pop_up_menu)
//            popUp.show()
//        }
    }

    private val noteListObserver = Observer<List<DrinkWaterEntity>> {
        listData.clear()
        listData.addAll(it)
        adapter.notifyDataSetChanged()
    }

    private val deleteObserver = Observer<Pair<Int, Int>> {
        if (it.first < 0) return@Observer
        Log.d("EEE",it.first.toString())
        if (it.first == 1) {
            listData.removeAt(it.second)
            adapter.notifyItemRemoved(it.second)
        }
    }
}
