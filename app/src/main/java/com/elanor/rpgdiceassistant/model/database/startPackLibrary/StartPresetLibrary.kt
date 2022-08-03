package com.elanor.rpgdiceassistant.model.database.startPackLibrary

import com.elanor.rpgdiceassistant.model.database.entities.DiceEntity
import com.elanor.rpgdiceassistant.model.database.entities.PresetEntity

interface StartPresetLibrary {
    val id: Int
    val presetName: String
    val preset: PresetEntity
        get() = PresetEntity(id, presetName)
    val dices: List<DiceEntity>

    companion object {
        const val ID_ZERO = 1
        const val ID_5E = 2
        const val ID_W40K = 3
        const val ID_WARCRY = 4
        const val ID_APOCALYPSE_WORLD = 5

    }
}
