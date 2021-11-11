package uz.gita.waterdrink.noteviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _changeImageLiveData = MutableLiveData<Unit>()
    val changeImageLiveData: LiveData<Unit> get() = _changeImageLiveData


    fun changeImage() {
        _changeImageLiveData.value = Unit
    }
}