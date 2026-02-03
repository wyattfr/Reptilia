package com.nightfall.reptilia.client

import com.nightfall.reptilia.entity.ModEntities
import com.nightfall.reptilia.entity.client.BeardedDragonRenderer
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

class ReptiliaClient : ClientModInitializer {

    override fun onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.BEARDED_DRAGON, ::BeardedDragonRenderer)
    }
}
