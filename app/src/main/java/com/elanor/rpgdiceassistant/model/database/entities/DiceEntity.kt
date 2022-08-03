package com.elanor.rpgdiceassistant.model.database.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.elanor.rpgdiceassistant.model.Dice

@Entity(
    tableName = "dice_table",
    foreignKeys = [ForeignKey(
        entity = PresetEntity::class,
        parentColumns = ["id"],
        childColumns = ["preset_id"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )],
    indices = [
        Index("preset_id")
    ]
)
data class DiceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "preset_id") val presetId: Int,
    var grain: Int,
    var image: String,
    var color: Int,
    var pack: String = "start"
) {

    fun toDice(): Dice = Dice(grain, value = 1, image, color, pack)


    companion object {
        fun toDiceEntity(dice: Dice, presetId: Int): DiceEntity = DiceEntity(
            grain = dice.grain,
            color = dice.color,
            pack = dice.pack,
            image = dice.image,
            id = 0,
            presetId = presetId
        )

    }
}