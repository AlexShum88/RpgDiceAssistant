package com.elanor.rpgdiceassistant.model.database

import androidx.room.*
import com.elanor.rpgdiceassistant.model.database.entities.PresetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PresetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPreset(preset: PresetEntity): Long

    @Query("Select * from presets")
    fun getPresets(): Flow<List<PresetEntity>>

    @Query("Select name from presets")
    fun getPresetsNames(): Flow<List<String>>

    @Delete
    suspend fun deletePreset(preset: PresetEntity)


    @Query("Select id from presets where name = :presetName")
    suspend fun getPresetIdByName(presetName: String): Int?

    @Query("Select name from presets where name = :presetId")
    fun getPresetNameById(presetId: Int): String?

    @Query("Select * from presets where name = :presetName")
    fun getPresetByName(presetName: String): PresetEntity?

    @Update()
    suspend fun updatePresetName(preset: PresetEntity)
}