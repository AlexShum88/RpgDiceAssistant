package com.elanor.rpgdiceassistant

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elanor.rpgdiceassistant.chooseColor.ChooseColorViewModel
import com.elanor.rpgdiceassistant.dicepool.DicePoolViewModel


class ModelFactory(
    private val app: App,
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            DicePoolViewModel::class.java -> DicePoolViewModel(app.switchColor) as T
            ChooseColorViewModel::class.java -> ChooseColorViewModel(app.switchColor) as T
            else -> throw IllegalArgumentException("unknown view model")
        }
    }


}


fun Fragment.factory() = ModelFactory(requireContext().applicationContext as App)