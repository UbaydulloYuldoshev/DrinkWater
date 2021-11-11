package uz.gita.waterdrink.noteviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.waterdrink.data.AppDatabase
import uz.gita.waterdrink.data.entity.DrinkWaterEntity

class NoteViewModel : ViewModel() {

    private val _noteListLiveData = MutableLiveData<List<DrinkWaterEntity>>()
    val noteListLiveData : LiveData<List<DrinkWaterEntity>> get() = _noteListLiveData
    private val _deleteLiveData = MutableLiveData<Pair<Int,Int>>()
    val deleteLiveData : LiveData<Pair<Int,Int>> get() = _deleteLiveData

    private val database = AppDatabase.getDatabase().getTaskDao()

    fun addNote(data :DrinkWaterEntity) {
        database.insert(data)
    }

    fun delete(data :DrinkWaterEntity, pos : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _deleteLiveData.postValue(Pair(database.delete(data), pos))
        }
    }

    fun loadData() {
        _noteListLiveData.value = database.getAllElements()
    }
}

