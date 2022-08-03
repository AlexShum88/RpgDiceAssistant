package com.elanor.rpgdiceassistant.model

import android.os.Parcelable
import com.elanor.rpgdiceassistant.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dice(
    var grain: Int,
    var value: Int,
    var image: String,
    var color: Int,
    var pack: String
) : Parcelable {
    companion object {
        const val IMAGE_PREFIX = "0-0"
        const val IMAGE_RES = ".png"
        const val DEFAULT_DICE_PACK = "start"
        const val DEFAULT_DICE_IMAGE = "start/6/1.png"
        const val DEFAULT_DICE_COLOR = R.color.white
        const val DEFAULT_DICE_GRAIN = 6
        const val FILE_NOT_FOUND = "file_not_found${IMAGE_RES}"
    }
}

