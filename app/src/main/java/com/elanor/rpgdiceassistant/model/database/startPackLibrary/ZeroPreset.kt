package com.elanor.rpgdiceassistant.model.database.startPackLibrary

import com.elanor.rpgdiceassistant.model.database.entities.DiceEntity

class ZeroPreset() : StartPresetLibrary {
    override val id: Int
        get() = StartPresetLibrary.ID_ZERO
    override val presetName: String
        get() = "Zero"
    override val dices: List<DiceEntity> = emptyList()
}