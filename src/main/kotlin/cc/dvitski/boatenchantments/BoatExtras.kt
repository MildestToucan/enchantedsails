package cc.dvitski.boatenchantments

import cc.dvitski.boatenchantments.enchantment.BoatEnchantments
import cc.dvitski.boatenchantments.entity.BoatAccessor
import cc.dvitski.boatenchantments.network.BoatItemStackPayload
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.core.registries.Registries
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.vehicle.AbstractBoat
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.HitResult

object BoatExtras {
    fun modifyPlacedBoat(boat: AbstractBoat, level: Level, hit: HitResult, stack: ItemStack, player: Player) {
        val accessor = boat as BoatAccessor
        accessor.itemStack = stack
    }

    fun modifyDroppedBoat(stack: ItemStack?, originalStack: ItemStack): ItemStack {
        return stack?.also { stack ->
            stack.applyComponents(originalStack.componentsPatch)
        } ?: originalStack;
    }

    fun sendBoatStackPayload(entity: Entity, stack: ItemStack) {
        val level = entity.level()
        if (level is ServerLevel) {
            val chunkMap = level.chunkSource.chunkMap
            val payload = BoatItemStackPayload(entity.id, stack)
            chunkMap.sendToTrackingPlayers(entity, ServerPlayNetworking.createS2CPacket(payload))
        }
    }

    fun addBoatSpeed(boat: AbstractBoat, stack: ItemStack, inputUp: Boolean, inputDown: Boolean): Float {
        if (!boat.isInWater) {
            return 0.0f
        }

        val registryAccess = boat.registryAccess()
        val enchantments = registryAccess.lookupOrThrow(Registries.ENCHANTMENT)

        val tailwindLevel = stack.enchantments.getLevel(enchantments.getOrThrow(BoatEnchantments.TAILWIND))
        return if (inputUp) {
            tailwindLevel * 0.04f / 2
        } else {
            if (inputDown) {
                tailwindLevel * -0.02f / 2
            } else {
                0.0f
            }
        }
    }
}
