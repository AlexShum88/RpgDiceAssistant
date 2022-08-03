package com.elanor.rpgdiceassistant.pack

import androidx.lifecycle.ViewModel
import com.elanor.rpgdiceassistant.model.PacksRepository

class PackViewModel : ViewModel() {

    val packsName = PacksRepository.NAMES
}