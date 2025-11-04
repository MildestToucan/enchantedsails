package cc.dvitski.boatenchantments.network

import cc.dvitski.boatenchantments.BoatEnchantmentsMod
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation

data class EntityGlintPayload(val id: Int, val glint: Boolean) : CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<EntityGlintPayload> {
        return BoatEnchantmentsPacketTypes.ENTITY_GLINT
    }

    companion object {
        val ID: ResourceLocation = ResourceLocation.fromNamespaceAndPath(BoatEnchantmentsMod.MOD_ID, "entity_glint")

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, EntityGlintPayload> = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, EntityGlintPayload::id,
            ByteBufCodecs.BOOL, EntityGlintPayload::glint,
            ::EntityGlintPayload,
        )
    }
}
