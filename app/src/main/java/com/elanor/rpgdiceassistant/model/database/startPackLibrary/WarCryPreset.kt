package com.elanor.rpgdiceassistant.model.database.startPackLibrary

import com.elanor.rpgdiceassistant.model.Dice
import com.elanor.rpgdiceassistant.model.database.entities.DiceEntity

class WarCryPreset : StartPresetLibrary {
    override val id: Int
        get() = StartPresetLibrary.ID_WARCRY
    override val presetName: String
        get() = "WarCry"
    override val dices: List<DiceEntity>
        get() = listOf(
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
                6,
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
                6,
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
                6,
                Dice.DEFAULT_DICE_IMAGE,
                Dice.DEFAULT_DICE_COLOR,
                Dice.DEFAULT_DICE_PACK
            ),
        )
}