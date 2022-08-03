package com.elanor.rpgdiceassistant.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.elanor.rpgdiceassistant.model.database.entities.DiceEntity
import com.elanor.rpgdiceassistant.model.database.entities.PresetEntity
import com.elanor.rpgdiceassistant.model.database.startPackLibrary.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    version = 1,
    entities = [
        DiceEntity::class,
        PresetEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDiceDao(): DiceDao
    abstract fun getPresetDao(): PresetDao


    private class DBCallBack(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    val dbPreset = it.getPresetDao()
                    val dbDice = it.getDiceDao()
                    startPresets.forEach {
                        dbPreset.insertPreset(it.preset)
                        dbDice.insertDices(it.dices)
                    }

                }
            }
        }
    }

    companion object {
        const val DATABASE_NAME = "database.db"
        private var INSTANCE: AppDatabase? = null

        fun getDataBase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(DBCallBack(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        val startPresets = listOf<StartPresetLibrary>(
            ZeroPreset(),
            ApocalypseWorldPreset(),
            WarCryPreset(),
            W40KPreset(),
            DnD5EPreset()
        )
    }
}