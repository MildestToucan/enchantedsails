package cc.dvitski.boatenchantments

import cc.dvitski.boatenchantments.enchantment.BoatEnchantments
import cc.dvitski.boatenchantments.network.BoatEnchantmentsPacketTypes
import cc.dvitski.boatenchantments.network.BoatItemStackPayload
import cc.dvitski.boatenchantments.tag.BoatEnchantmentsItemTags
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.BoatItem
import net.minecraft.world.item.enchantment.Enchantable
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object BoatEnchantmentsMod : ModInitializer {
    const val MOD_ID = "boatenchantments"
    const val MOD_NAME = "Boat Enchantments"

    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    @Suppress("UnusedExpression")
    override fun onInitialize() {
        logger.info("Initializing $MOD_NAME")

        BoatExtras

        BoatEnchantments
        BoatEnchantmentsItemTags

        PayloadTypeRegistry.playS2C().register(BoatEnchantmentsPacketTypes.BOAT_ITEM_STACK, BoatItemStackPayload.STREAM_CODEC)

        DefaultItemComponentEvents.MODIFY.register { context ->
            context.modify(BoatItem::class::isInstance) { builder, _ ->
                // TODO mod opt-out api

                builder.getOrCreate(DataComponents.ENCHANTABLE) { Enchantable(1) }
            }
        }
    }
}
