package com.elanor.rpgdiceassistant.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.elanor.rpgdiceassistant.model.database.entities.DiceEntity

@Dao
interface DiceDao {

    @Query("SELECT * from dice_table where preset_id = :preset_id")
    suspend fun getAllDicesByPresetId(preset_id: Int): List<DiceEntity>

    @Insert
    suspend fun insertDices(dices: List<DiceEntity>)

}