package com.nightfall.reptilia.entity.client

import com.nightfall.reptilia.entity.BeardedDragonEntity
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import software.bernie.geckolib.renderer.GeoEntityRenderer

class BeardedDragonRenderer(context: EntityRendererFactory.Context) :
    GeoEntityRenderer<BeardedDragonEntity, LivingEntityRenderState>(context, BeardedDragonModel())

