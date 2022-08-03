package com.elanor.rpgdiceassistant.model

import com.elanor.rpgdiceassistant.model.Dice.Companion.IMAGE_PREFIX
import com.elanor.rpgdiceassistant.model.Dice.Companion.IMAGE_RES
import kotlin.random.Random


object DiceActions {

    //    const val IMAGE_RES = ".png"
    val stat = Statistic

    fun rollDice(dice: Dice) {
        val dicesForStat = mutableListOf<Dice>()
        dicesForStat.add(innerRollDice(dice).copy())
        stat.thenDicesRoll(dicesForStat)
    }

    private fun innerRollDice(dice: Dice): Dice {
        val grain = dice.grain
        val diceR = findDice(dice)
        diceR.value = Random(System.nanoTime()).nextInt(1, grain + 1)
        changeDiceImage(diceR)
        notifyRepositoryListeners()
        return diceR
    }

    fun changeDiceImage(dice: Dice) {
        dice.image = "${dice.pack}/${dice.grain}/$IMAGE_PREFIX${dice.value}$IMAGE_RES"
    }

    fun rollAllPreviousDices(dice: Dice) {
        val dices = DiceRepository.getDices()
        val index = dices.indexOfFirst { it === dice }
        if (index < 0) return
        val dicesForStat = mutableListOf<Dice>()
        (0..index).forEach {
            innerRollDice(dices[it])
            dicesForStat.add(dices[it].copy())
        }
        stat.thenDicesRoll(dicesForStat)
//        noActiveDicesToDefault(index, dices)
    }

    private fun noActiveDicesToDefault(index: Int, dices: List<Dice>) {
        (index + 1..dices.lastIndex).forEach {
            dices[it].value = 1
            changeDiceImage(dices[it])
        }
        notifyRepositoryListeners()
    }

    fun changeGrain(dice: Dice, newGrain: Int) {
        val diceC = findDice(dice)
        diceC.grain = newGrain
        diceC.value = 1
        changeDiceImage(diceC)
        notifyRepositoryListeners()
    }

    fun changeDiceColor(dice: Dice, color: Int) {
        val diceR = findDice(dice)
        diceR.color = color
        notifyRepositoryListeners()
    }


    private fun findDice(dice: Dice): Dice {
        return DiceRepository.getDices().firstOrNull { it === dice }
            ?: throw IllegalArgumentException("no such dice")
    }

    private fun findDice(dice: Dice, list: List<Dice>): Dice {
        return list.firstOrNull { it === dice }
            ?: throw IllegalArgumentException("no such dice")
    }

    private fun notifyRepositoryListeners() {
        DiceRepository.notifyListeners()
    }


}