package com.elanor.rpgdiceassistant.statistic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elanor.rpgdiceassistant.model.Statistic
import com.elanor.rpgdiceassistant.model.StatisticListener
import com.elanor.rpgdiceassistant.model.StatisticObject
import kotlinx.coroutines.launch

class StatisticViewModel : ViewModel() {
    private val _statistic = MutableLiveData<List<StatisticObject>>()
    val statistic: LiveData<List<StatisticObject>> = _statistic
    val statisticListener: StatisticListener = {
        _statistic.value = it
    }

    init {
        viewModelScope.launch {
            Statistic.listenStatistic().collect {
                _statistic.value = it
            }
        }
    }

    fun create() {
//        Statistic.addListener(statisticListener)

    }

//    fun destroy() {
//        Statistic.removeListener(statisticListener)
//    }


}