package com.elanor.rpgdiceassistant

import android.app.Application
import com.elanor.rpgdiceassistant.model.*
import com.elanor.rpgdiceassistant.model.database.AppDatabase
import com.elanor.rpgdiceassistant.model.databaseRepository.RoomPresetRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {
    val switchColor = SwitchColor()
    val diceRepository = DiceRepository
    val colorRepository = ColorsRepository
    val statistic = Statistic
    val rollRegime = RollRegime
    val database by lazy {
        AppDatabase.getDataBase(
            applicationContext,
            CoroutineScope(SupervisorJob())
        )
    }
    val roomPresetRepository by lazy {
        RoomPresetRepository(
            database.getPresetDao(),
            database.getDiceDao()
        )
    }
}