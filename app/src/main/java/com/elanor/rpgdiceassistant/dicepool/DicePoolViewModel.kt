package com.elanor.rpgdiceassistant.dicepool

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elanor.rpgdiceassistant.model.*
import com.elanor.rpgdiceassistant.model.Dice.Companion.DEFAULT_DICE_COLOR

class DicePoolViewModel(
    private val switchColor: SwitchColor
) : ViewModel() {

    private val _dicesLD = MutableLiveData<List<Dice>>()
    val dicesLD: LiveData<List<Dice>> = _dicesLD

    private val diceListListener: DiceListListener = {
        _dicesLD.value = it
    }

    private val _colorSwitcher = MutableLiveData<SwitchColor>()
    val colorSwitcher: LiveData<SwitchColor> = _colorSwitcher

    private val colorListener: SwitchColorListener = {
        _colorSwitcher.value = it
    }

    fun onCreate(currentPack: String) {
        DiceRepository.addListener(diceListListener)
        switchColor.addListener(colorListener)

        if (DiceRepository.getDices().isEmpty()) {
            createStartDice()
        }
        DiceRepository.changeDicePack(currentPack)
    }

    fun onDestroy() {
        DiceRepository.removeListener(diceListListener)
        switchColor.removeListener(colorListener)
    }

    private fun createStartDice() {
        DiceFactory.createDices(number = 6)
            .forEach { DiceRepository.addDice(it) }
        _dicesLD.value = DiceRepository.getDices()
    }

    fun rollDice(dice: Dice) {
        DiceActions.rollDice(dice)
    }

    fun rollPreviousDices(dice: Dice) {
        DiceActions.rollAllPreviousDices(dice)
    }

    fun addDice(grain: Int, color: Int, currentPack: String) {
        val dice = DiceFactory.createDice(grain = grain, color = color, pack = currentPack)
        DiceRepository.addDice(dice)
    }

    fun changeColor(dice: Dice) {
        DiceActions.changeDiceColor(
            dice,
            colorSwitcher.value?.color ?: DEFAULT_DICE_COLOR,
        )
    }

    fun changeColorOfAllDices() {
        _dicesLD.value?.forEach { changeColor(it) } ?: return
    }

    fun startChangeColorRegime() {
        switchColor.switch = true
    }

    fun endChangeColorRegime() {
        switchColor.switch = false
    }

    fun changeGrain(dice: Dice, newGrain: Int) {
        DiceActions.changeGrain(dice, newGrain)
    }

    fun changeGrainForAllDices(newGrain: Int) {
        _dicesLD.value!!.forEach {
            DiceActions.changeGrain(it, newGrain)
        }
    }

    fun removeDice(dice: Dice) {
        DiceRepository.removeDice(dice)
    }

}