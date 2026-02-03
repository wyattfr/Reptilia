package com.nightfall.reptilia.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.passive.TameableEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil

class BeardedDragonEntity(
    type: EntityType<out TameableEntity>,
    world: World
) : TameableEntity(type, world), GeoEntity {

    private val cache = GeckoLibUtil.createInstanceCache(this)

    companion object {
        fun createBeardedDragonAttributes(): DefaultAttributeContainer.Builder {
            return createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.FOLLOW_RANGE, 16.0)
        }
    }

    override fun initGoals() {
        goalSelector.add(1, SwimGoal(this))
        goalSelector.add(2, SitGoal(this))
        goalSelector.add(3, FollowOwnerGoal(this, 1.0, 4f, 2f))
        goalSelector.add(4, WanderAroundGoal(this, 0.8))
        goalSelector.add(5, LookAtEntityGoal(this, PlayerEntity::class.java, 6f))
    }

    override fun isBreedingItem(stack: ItemStack?): Boolean {
        TODO("Not yet implemented")
    }

    override fun interactMob(player: PlayerEntity, hand: Hand): ActionResult {
        val stack = player.getStackInHand(hand)

        if (!isTamed && stack.isOf(Items.WHEAT_SEEDS)) {
            if (!player.isCreative) stack.decrement(1)

            if (!entityWorld.isClient && random.nextInt(3) == 0) {
                owner = player
                setTamed(true, true)
                navigation.stop()
                isSitting = true
                entityWorld.sendEntityStatus(this, 7.toByte())
            }

            return ActionResult.SUCCESS
        }

        if (isOwner(player)) {
            if (!entityWorld.isClient) {
                isSitting = !isSitting
            }
            return ActionResult.SUCCESS
        }

        return super.interactMob(player, hand)
    }

    override fun createChild(world: ServerWorld, mate: PassiveEntity): PassiveEntity {
        val baby = BeardedDragonEntity(ModEntities.BEARDED_DRAGON, world)
        baby.owner = this.owner
        baby.setTamed(true, true)
        return baby
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController<BeardedDragonEntity>("movement", 5) { state ->
                if (state.isMoving) {
                    return@AnimationController state.setAndContinue(RawAnimation.begin().thenLoop("animation.bearded_dragon.walk"))
                } else {
                    return@AnimationController state.setAndContinue(RawAnimation.begin().thenLoop("animation.bearded_dragon.idle"))
                }
            }
        )
    }


    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }
}