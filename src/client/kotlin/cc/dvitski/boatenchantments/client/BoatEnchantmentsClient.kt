package cc.dvitski.boatenchantments.client

import cc.dvitski.boatenchantments.BoatEnchantmentsMod.MOD_ID
import cc.dvitski.boatenchantments.BoatEnchantmentsMod.MOD_NAME
import cc.dvitski.boatenchantments.network.BoatEnchantmentsPacketTypes
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object BoatEnchantmentsClient : ClientModInitializer {
    val logger: Logger = LoggerFactory.getLogger("$MOD_ID-client")

	override fun onInitializeClient() {
        logger.info("Initializing $MOD_NAME client")

        ClientPlayNetworking.registerGlobalReceiver(BoatEnchantmentsPacketTypes.ENTITY_GLINT) { payload, context ->
            val client = context.client()
            client.level?.let { level ->
                level.getEntity(payload.id)?.let { entity ->
                    ClientBoatExtras.receiveGlintPayload(entity, payload.glint)
                }
            }
        }
	}
}
