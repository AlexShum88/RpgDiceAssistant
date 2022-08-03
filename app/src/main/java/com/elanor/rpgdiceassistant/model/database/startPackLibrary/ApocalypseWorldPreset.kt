package com.elanor.rpgdiceassistant.model.database.startPackLibrary

import com.elanor.rpgdiceassistant.model.Dice
import com.elanor.rpgdiceassistant.model.database.entities.DiceEntity

class ApocalypseWorldPreset : StartPresetLibrary {
    override val id = StartPresetLibrary.ID_APOCALYPSE_WORLD
    override val presetName: String
        get() = "Apocalypse world"
    override val dices: List<DiceEntity>
        get() = listOf<DiceEntity>(
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