package cc.dvitski.enchantedsails.client

import cc.dvitski.enchantedsails.EnchantedSails.MOD_ID
import cc.dvitski.enchantedsails.EnchantedSails.MOD_NAME
import cc.dvitski.enchantedsails.network.ESPacketTypes
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object EnchantedSailsClient : ClientModInitializer {
    val logger: Logger = LoggerFactory.getLogger("$MOD_ID-client")

	override fun onInitializeClient() {
        logger.info("Initializing $MOD_NAME client")

        ClientPlayNetworking.registerGlobalReceiver(ESPacketTypes.BOAT_ITEM_STACK) { payload, context ->
            val client = context.client()
            client.level?.let { level ->
                level.getEntity(payload.id)?.let { entity ->
                    ClientBoatHandler.receiveItemStackPayload(entity, payload.stack)
                }
            }
        }
	}
}
