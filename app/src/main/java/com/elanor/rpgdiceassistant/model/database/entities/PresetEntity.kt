package com.elanor.rpgdiceassistant.model.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "presets",
    indices = [
        Index("name", unique = true)
    ]
)
data class PresetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String
) {

    companion object {
        fun toPresetEntity(name: String): PresetEntity = PresetEntity(name = name, id = 0)
    }
}