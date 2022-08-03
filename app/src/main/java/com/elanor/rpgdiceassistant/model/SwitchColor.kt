package com.elanor.rpgdiceassistant.model

import com.elanor.rpgdiceassistant.R

typealias SwitchColorListener = (switchColor: SwitchColor) -> Unit


class SwitchColor {
    private var _color: Int = R.color.black
    var color: Int
        get() = _color
        set(value) {
            _color = value
            notifyListeners()
        }
    private var _switch: Boolean = false
    var switch: Boolean
        get() = _switch
        set(value) {
            _switch = value
            notifyListeners()
        }
    val listeners = mutableSetOf<SwitchColorListener>()

    fun addListener(listener: SwitchColorListener) {
        listeners.add(listener)
        notifyListeners()
    }

    fun removeListener(listener: SwitchColorListener) {
        listeners.remove(listener)
    }

    private fun notifyListeners() {
        listeners.forEach {
            it.invoke(this)
        }
    }

}
