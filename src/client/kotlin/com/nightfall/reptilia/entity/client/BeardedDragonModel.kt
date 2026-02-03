package com.nightfall.reptilia.entity.client

import com.nightfall.reptilia.entity.BeardedDragonEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.DefaultedEntityGeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

class BeardedDragonModel : DefaultedEntityGeoModel<BeardedDragonEntity>(
    Identifier.of("reptilia", "bearded_dragon")
) {
    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.of("reptilia", "entity/base_morph.png")
    }
}
