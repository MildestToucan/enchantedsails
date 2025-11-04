package cc.dvitski.boatenchantments.data

import cc.dvitski.boatenchantments.data.client.LanguageProvider
import cc.dvitski.boatenchantments.data.server.EnchantmentProvider
import cc.dvitski.boatenchantments.data.server.EnchantmentTagProvider
import cc.dvitski.boatenchantments.data.server.ItemTagProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object BoatEnchantmentsDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()

        pack.addProvider(::EnchantmentProvider)

        pack.addProvider(::ItemTagProvider)
        pack.addProvider(::EnchantmentTagProvider)

        pack.addProvider(::LanguageProvider)
	}
}
