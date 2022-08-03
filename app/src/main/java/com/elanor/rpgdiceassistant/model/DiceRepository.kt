package com.elanor.rpgdiceassistant.model

typealias DiceListListener = (dices: List<Dice>) -> Unit

object DiceRepository {


    private val dices = mutableListOf<Dice>()

    private val listeners = mutableSetOf<DiceListListener>()

    fun addDice(dice: Dice) {
        dices.add(dice)
        notifyListeners()
    }

    fun addDicesFromPreset(dices: List<Dice>) {
        this.dices.clear()
        dices.forEach { this.dices.add(it) }
        notifyListeners()
    }


    fun removeDice(dice: Dice) {
        dices.remove(dice)
        notifyListeners()
    }

    fun getDices(): MutableList<Dice> = dices

    fun changeDicePack(currentPack: String) {
        dices.forEach {
            it.pack = currentPack
            DiceActions.changeDiceImage(it)
        }
        notifyListeners()
    }

    fun addListener(listener: DiceListListener) {
        listeners.add(listener)
        listener.invoke(dices)
    }

    fun removeListener(listener: DiceListListener) {
        listeners.remove(listener)
    }

    fun notifyListeners() {
        listeners.forEach {
            it.invoke(dices)
        }
    }


}