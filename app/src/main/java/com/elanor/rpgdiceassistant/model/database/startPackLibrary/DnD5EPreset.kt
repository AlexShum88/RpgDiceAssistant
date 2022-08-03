package com.elanor.rpgdiceassistant.model.database.startPackLibrary

import com.elanor.rpgdiceassistant.model.Dice
import com.elanor.rpgdiceassistant.model.database.entities.DiceEntity

class DnD5EPreset : StartPresetLibrary {
    override val id: Int
        get() = StartPresetLibrary.ID_5E
    override val presetName: String
        get() = "DnD5E"
    override val dices: List<DiceEntity>
        get() = listOf(
            DiceEntity(
                0,
                id,
                20,
                Dice.DEFAULT_DICE_IMAGE,
                Dice.DEFAULT_DICE_COLOR,
                Dice.DEFAULT_DICE_PACK
            ),
            DiceEntity(
                0,
                id,
                20,
                Dice.DEFAULT_DICE_IMAGE,
                Dice.DEFAULT_DICE_COLOR,
                Dice.DEFAULT_DICE_PACK
            ),
            DiceEntity(
                0,
                id,
                4,
                Dice.DEFAULT_DICE_IMAGE,
                Dice.DEFAULT_DICE_COLOR,
                Dice.DEFAULT_DICE_PACK
            ),
            DiceEntity(
                0,
                id,
                6,
                Dice.DEFAULT_DICE_IMAGE,
                Dice.DEFAULT_DICE_COLOR,
                Dice.DEFAULT_DICE_PACK
            ),
            DiceEntity(
                0,
                id,
                8,
                Dice.DEFAULT_DICE_IMAGE,
                Dice.DEFAULT_DICE_COLOR,
                Dice.DEFAULT_DICE_PACK
            ),
            DiceEntity(
                0,
                id,
                10,
                Dice.DEFAULT_DICE_IMAGE,
                Dice.DEFAULT_DICE_COLOR,
                Dice.DEFAULT_DICE_PACK
            ),
            DiceEntity(
                0,
                id,
                10,
                Dice.DEFAULT_DICE_IMAGE,
                Dice.DEFAULT_DICE_COLOR,
                Dice.DEFAULT_DICE_PACK
            ),
            DiceEntity(
                0,
                id,
                12,
                Dice.DEFAULT_DICE_IMAGE,
                Dice.DEFAULT_DICE_COLOR,
                Dice.DEFAULT_DICE_PACK
            ),
        )

}