package com.nightfall.reptilia.entity

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.world.World

object ModEntities {

    private val BEARDED_DRAGON_ID = Identifier.of("reptilia", "bearded_dragon")

    val BEARDED_DRAGON: EntityType<BeardedDragonEntity> = Registry.register(
        Registries.ENTITY_TYPE,
        BEARDED_DRAGON_ID,
        EntityType.Builder.create(
            { type: EntityType<BeardedDragonEntity>, world: World ->
                BeardedDragonEntity(type, world)
            },
            SpawnGroup.CREATURE
        )
            .dimensions(1.2f, 0.8f)
            .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, BEARDED_DRAGON_ID))
    )

    @Suppress("unused")
    fun init() {
        // Register entity attributes
        FabricDefaultAttributeRegistry.register(BEARDED_DRAGON, BeardedDragonEntity.createBeardedDragonAttributes())
    }
}