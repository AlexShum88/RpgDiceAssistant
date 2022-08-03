package com.elanor.rpgdiceassistant.dicepool

import com.elanor.rpgdiceassistant.model.Dice

interface AdapterActions {
    fun onClickRoll(dice: Dice)
    fun setChangeAlphaRegime(): Boolean
}