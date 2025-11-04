package cc.dvitski.boatenchantments.client

import cc.dvitski.boatenchantments.BoatEnchantments.MOD_ID
import cc.dvitski.boatenchantments.BoatEnchantments.MOD_NAME
import net.fabricmc.api.ClientModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object BoatEnchantmentsClient : ClientModInitializer {
    val logger: Logger = LoggerFactory.getLogger("$MOD_ID-client")

	override fun onInitializeClient() {
        logger.info("Initializing $MOD_NAME client")
	}
}
