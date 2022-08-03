package com.elanor.rpgdiceassistant.dicepool


import android.view.Menu
import android.view.View
import android.widget.PopupMenu
import com.elanor.rpgdiceassistant.R
import com.elanor.rpgdiceassistant.model.Dice
import com.elanor.rpgdiceassistant.model.DiceGrains


class PopUp(
    private val view: View,
    private val actions: DicePopUpAction
) {

    val dice =
        if (view.tag is Dice) view.tag as Dice else throw IllegalArgumentException("wrong view tag in pop")

    fun createDicePop() {

        val popUp = PopupMenu(view.context, view)

        popUp.menu.add(0, 1, Menu.NONE, R.string.change_grain)

        popUp.menu.add(0, 2, Menu.NONE, R.string.change_all_grains)

        popUp.menu.add(0, 3, Menu.NONE, R.string.change_color_of_all_dice)

        popUp.menu.add(0, 4, Menu.NONE, R.string.remove_dice)

        popUp.setOnMenuItemClickListener {

            when (it.itemId) {
                CHANGE_GRAIN -> {
                    createGrainMenu(it.itemId)
                }
                CHANGE_ALL_GRAINS -> {
                    createGrainMenu(it.itemId)
                }
                CHANGE_COLOR_OF_ALL_DICE -> {
                    actions.changeColorOfAllDices()
                }
                REMOVE_DICE -> {
                    actions.removeDice(dice)
                }

            }
            return@setOnMenuItemClickListener true
        }
        popUp.show()
    }

    private fun createGrainMenu(id: Int) {
        val pop = PopupMenu(view.context, view)
        var i = 0
        val grainsID = mutableMapOf<Int, Int>()
        DiceGrains.GRAINS.forEach {
            i++
            pop.menu.add(
                0,
                i,
                Menu.NONE,
                view.context.getString(R.string.grain_menu_text, it.toString())
            )
            grainsID[i] = it
        }
        pop.setOnMenuItemClickListener {
            when (id) {
                1 -> actions.changeGrain(dice, grainsID[it.itemId]!!)
                2 -> actions.changeAllGrain(grainsID[it.itemId]!!)
                else -> return@setOnMenuItemClickListener false
            }
            return@setOnMenuItemClickListener true
        }
        pop.show()

    }

    companion object {
        const val CHANGE_GRAIN = 1
        const val CHANGE_ALL_GRAINS = 2
        const val CHANGE_COLOR_OF_ALL_DICE = 3
        const val REMOVE_DICE = 4
    }
}