package cc.dvitski.boatenchantments.network

import net.minecraft.network.protocol.common.custom.CustomPacketPayload

object BoatEnchantmentsPacketTypes {
    val BOAT_ITEM_STACK: CustomPacketPayload.Type<BoatItemStackPayload> = CustomPacketPayload.Type(BoatItemStackPayload.ID)
}
