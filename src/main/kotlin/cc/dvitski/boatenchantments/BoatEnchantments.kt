package cc.dvitski.boatenchantments

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object BoatEnchantments : ModInitializer {
    const val MOD_ID = "boatenchantments"
    const val MOD_NAME = "Boat Enchantments"

    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        logger.info("Initializing $MOD_NAME")
    }
}
