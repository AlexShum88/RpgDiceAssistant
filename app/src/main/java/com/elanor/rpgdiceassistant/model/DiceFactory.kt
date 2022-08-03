package com.elanor.rpgdiceassistant.model

import com.elanor.rpgdiceassistant.model.Dice.Companion.DEFAULT_DICE_COLOR
import com.elanor.rpgdiceassistant.model.Dice.Companion.DEFAULT_DICE_GRAIN
import com.elanor.rpgdiceassistant.model.Dice.Companion.DEFAULT_DICE_IMAGE
import com.elanor.rpgdiceassistant.model.Dice.Companion.DEFAULT_DICE_PACK

object DiceFactory {
//    const val DEFAULT_DICE_PACK = "start"
//    const val DEFAULT_DICE_IMAGE = "start/6/1.png"
//    const val DEFAULT_DICE_COLOR = R.color.white
//    const val DEFAULT_DICE_GRAIN = 6


    fun createDice(
        grain: Int = DEFAULT_DICE_GRAIN,
        color: Int = DEFAULT_DICE_COLOR,
        image: String = DEFAULT_DICE_IMAGE,
        pack: String = DEFAULT_DICE_PACK
    ): Dice = Dice(grain = grain, color = color, image = image, value = 1, pack = pack).also {
        DiceActions.changeDiceImage(it)
    }


    fun createDices(
        number: Int,
        grain: Int = DEFAULT_DICE_GRAIN,
        color: Int = DEFAULT_DICE_COLOR,
        image: String = DEFAULT_DICE_IMAGE,
        pack: String = DEFAULT_DICE_PACK
    ): List<Dice> {
        return (1..number).map {
            createDice(grain = grain, color = color, image = image, pack = pack)
        }.toList()
    }

    fun createDicePack(exampleDice: Dice): List<Dice> {
        return DiceGrains.GRAINS.map {
            exampleDice.copy(grain = it)
        }
    }


}