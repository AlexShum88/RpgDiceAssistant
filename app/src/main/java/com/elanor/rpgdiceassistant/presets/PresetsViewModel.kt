package com.elanor.rpgdiceassistant.presets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.elanor.rpgdiceassistant.model.Dice
import com.elanor.rpgdiceassistant.model.DiceRepository
import com.elanor.rpgdiceassistant.model.database.entities.PresetEntity
import com.elanor.rpgdiceassistant.model.databaseRepository.RoomPresetRepository
import kotlinx.coroutines.launch

class PresetsViewModel(
    private val roomPresetRepository: RoomPresetRepository,
) : ViewModel() {

    val presetsList = roomPresetRepository.getPresetsWithDice().asLiveData()
    val presetNames = roomPresetRepository.getPresetsNames().asLiveData()

    fun insertPreset(name: String, dices: List<Dice>) = viewModelScope.launch {
        roomPresetRepository.setPresetsWithDices(name, dices)
    }

    fun changePresetName(preset: PresetEntity) = viewModelScope.launch {
        roomPresetRepository.changePresetName(preset)
    }

    fun presetGetDices(preset: String) = viewModelScope.launch {
        val dss = roomPresetRepository.getDices(preset)
        println("dices from DB name ${dss}")
    }

    fun presetSetDicesToDiceRepository(dices: List<Dice>, currentPack: String) {
        DiceRepository.addDicesFromPreset(dices)
        DiceRepository.changeDicePack(currentPack)
    }

    fun deletePreset(preset: PresetEntity) = viewModelScope.launch {
        roomPresetRepository.deletePreset(preset)
    }


//    fun insert(word: Word) = viewModelScope.launch {
//        repository.insert(word)
//    }
}