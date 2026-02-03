package com.nightfall.reptilia

import com.nightfall.reptilia.entity.ModEntities
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

class Reptilia : ModInitializer {

    companion object {
        const val MOD_ID = "reptilia"
        val LOGGER = LoggerFactory.getLogger(MOD_ID)
    }

    override fun onInitialize() {
        LOGGER.info("[REPTILIA] Mod initialized!")

        ModEntities.init()
    }
}
