package cc.dvitski.boatenchantments.network

import net.minecraft.network.protocol.common.custom.CustomPacketPayload

object BoatEnchantmentsPacketTypes {
    val ENTITY_GLINT: CustomPacketPayload.Type<EntityGlintPayload> = CustomPacketPayload.Type(EntityGlintPayload.ID)
}
