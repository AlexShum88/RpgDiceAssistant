package com.elanor.rpgdiceassistant.dicepool

import com.elanor.rpgdiceassistant.model.Dice

interface DicePopUpAction {
    fun removeDice(dice: Dice)
    fun changeGrain(dice: Dice, grain: Int)
    fun changeAllGrain(grain: Int)
    fun changeColorOfAllDices()
    //else
}