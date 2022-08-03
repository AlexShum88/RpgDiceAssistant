package com.elanor.rpgdiceassistant.chooseColor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elanor.rpgdiceassistant.model.ColorsRepository
import com.elanor.rpgdiceassistant.model.DColor
import com.elanor.rpgdiceassistant.model.DColorsListener
import com.elanor.rpgdiceassistant.model.SwitchColor
import kotlinx.coroutines.launch

class ChooseColorViewModel(
    private val switchColor: SwitchColor
) : ViewModel() {

    private val _colors = MutableLiveData<List<DColor>>()
    val colors: LiveData<List<DColor>> = _colors

    private val colorListener: DColorsListener = {
        _colors.value = it
    }

    fun onCreate() {


        viewModelScope.launch {
            ColorsRepository.listenCurrentColor().collect {
                _colors.value = it
            }
        }

        val dColor =
            ColorsRepository.getColors().firstOrNull { it.backgroundColor == switchColor.color }
                ?: return
        ColorsRepository.changeChecked(dColor)
    }


    fun chooseColor(color: DColor) {
        if (ColorsRepository.changeChecked(color)) switchColor.color = color.backgroundColor
    }
}