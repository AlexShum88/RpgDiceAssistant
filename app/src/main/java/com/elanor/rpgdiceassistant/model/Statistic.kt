package com.elanor.rpgdiceassistant.model

import android.text.format.DateFormat
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import java.util.*

//typealias StatisticListener = (Map<String, List<Dice>>)->Unit
typealias StatisticListener = (List<StatisticObject>) -> Unit
typealias TotalScoreListener = (Int) -> Unit

object Statistic {

    private val listeners = mutableSetOf<StatisticListener>()
    private val listenersTotalScore = mutableSetOf<TotalScoreListener>()
    private val statistic = mutableListOf<StatisticObject>()
    private var totalScore = 0

    fun getStatistic(): List<StatisticObject> {
        return statistic
    }

    fun thenDicesRoll(dices: List<Dice>) {
        val date = DateFormat.format("HH:mm:ss", Date().time).toString()
        statistic.add(StatisticObject(date, dices))
        totalScore = dices.map { it.value }.reduce { acc, i -> acc + i }
        notifyTotalScoreListeners()
        notifyListeners()
    }

//    fun addListener(listener: StatisticListener){
//        listeners.add(listener)
//        listener.invoke(statistic)
//    }
//
//    fun removeListener(listener: StatisticListener){
//        listeners.remove(listener)
//    }

    fun listenStatistic(): Flow<List<StatisticObject>> = callbackFlow {
        val listener: StatisticListener = {
            trySend(it)
        }
        listeners.add(listener)
        listener.invoke(statistic)
        awaitClose {
            listeners.remove(listener)
        }
    }.buffer(Channel.CONFLATED)

    private fun notifyListeners() {
        listeners.forEach {
            it.invoke(statistic)
        }
    }

    fun listenTotalScore(): Flow<Int> = callbackFlow {
        val listener: TotalScoreListener = {
            trySend(it)
        }
        listenersTotalScore.add(listener)
        listener.invoke(totalScore)
        awaitClose {
            listenersTotalScore.remove(listener)
        }
    }.buffer(Channel.CONFLATED)

    private fun notifyTotalScoreListeners() {
        listenersTotalScore.forEach {
            it.invoke(totalScore)
        }
    }

}