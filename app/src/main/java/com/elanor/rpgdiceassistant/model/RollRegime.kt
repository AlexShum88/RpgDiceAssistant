package com.elanor.rpgdiceassistant.model

object RollRegime {
    var rollAllPrevDIcesRegime = false

    fun changeRollRegime() {
        rollAllPrevDIcesRegime = !rollAllPrevDIcesRegime
    }
}