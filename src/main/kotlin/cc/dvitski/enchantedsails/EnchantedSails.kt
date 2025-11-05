package cc.dvitski.enchantedsails

import cc.dvitski.enchantedsails.enchantment.ESBoatEnchantments
import cc.dvitski.enchantedsails.network.BoatItemStackPayload
import cc.dvitski.enchantedsails.network.ESPacketTypes
import cc.dvitski.enchantedsails.tag.ESItemTags
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.BoatItem
import net.minecraft.world.item.enchantment.Enchantable
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object EnchantedSails : ModInitializer {
    const val MOD_ID = "enchantedsails"
    const val MOD_NAME = "Enchanted Sails"

    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    @Suppress("UnusedExpression")
    override fun onInitialize() {
        logger.info("Initializing $MOD_NAME")

        BoatHandler

        ESBoatEnchantments
        ESItemTags

        PayloadTypeRegistry.playS2C().register(ESPacketTypes.BOAT_ITEM_STACK, BoatItemStackPayload.STREAM_CODEC)

        DefaultItemComponentEvents.MODIFY.register { context ->
            context.modify(BoatItem::class::isInstance) { builder, _ ->
                // TODO mod opt-out api

                builder.getOrCreate(DataComponents.ENCHANTABLE) { Enchantable(1) }
            }
        }
    }
}
