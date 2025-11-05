package cc.dvitski.enchantedsails.network

import net.minecraft.network.protocol.common.custom.CustomPacketPayload

object ESPacketTypes {
    val BOAT_ITEM_STACK: CustomPacketPayload.Type<BoatItemStackPayload> = CustomPacketPayload.Type(BoatItemStackPayload.ID)
}
