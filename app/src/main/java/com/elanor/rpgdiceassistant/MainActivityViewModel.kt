package com.elanor.rpgdiceassistant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elanor.rpgdiceassistant.model.Statistic
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _totalScoreLD: MutableLiveData<Int> = MutableLiveData<Int>()
    val totalScoreLD: LiveData<Int> = _totalScoreLD

    init {
        viewModelScope.launch {
            Statistic.listenTotalScore().collect {
                _totalScoreLD.value = it
            }
        }
    }
}