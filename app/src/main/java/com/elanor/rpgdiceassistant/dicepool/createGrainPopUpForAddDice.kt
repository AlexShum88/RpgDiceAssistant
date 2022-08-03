package com.elanor.rpgdiceassistant.dicepool

import android.view.Menu
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.elanor.rpgdiceassistant.R
import com.elanor.rpgdiceassistant.model.DiceGrains


fun createGrainPopUpForAddDice(
    view: View,
    color: Int,
    pack: String,
    action: (Int, Int, String) -> Unit
) {
    val popUp = PopupMenu(view.context, view)
    var i = 0
    val grainsID = mutableMapOf<Int, Int>()
    DiceGrains.GRAINS.forEach {
        i++
        popUp.menu.add(
            0, i, Menu.NONE, view.context.getString(R.string.grain_menu_text, it.toString())
        )
        grainsID[i] = it
    }
    popUp.setOnMenuItemClickListener {
        val grain = grainsID[it.itemId]!!
        action(grain, color, pack)
        return@setOnMenuItemClickListener true
    }
    popUp.show()
}
//}