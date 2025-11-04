package cc.dvitski.boatenchantments

import cc.dvitski.boatenchantments.entity.BoatAccessor
import cc.dvitski.boatenchantments.network.EntityGlintPayload
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
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

    fun sendGlintPayload(entity: Entity, glint: Boolean) {
        val level = entity.level()
        if (level is ServerLevel) {
            val chunkMap = level.chunkSource.chunkMap
            val payload = EntityGlintPayload(entity.id, glint)
            chunkMap.sendToTrackingPlayers(entity, ServerPlayNetworking.createS2CPacket(payload))
        }
    }
}
