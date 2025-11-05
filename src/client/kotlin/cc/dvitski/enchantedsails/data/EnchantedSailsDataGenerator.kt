package cc.dvitski.enchantedsails.data

import cc.dvitski.enchantedsails.data.client.LanguageProvider
import cc.dvitski.enchantedsails.data.server.EnchantmentProvider
import cc.dvitski.enchantedsails.data.server.EnchantmentTagProvider
import cc.dvitski.enchantedsails.data.server.ItemTagProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object EnchantedSailsDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()

        pack.addProvider(::EnchantmentProvider)

        pack.addProvider(::ItemTagProvider)
        pack.addProvider(::EnchantmentTagProvider)

        pack.addProvider(::LanguageProvider)
	}
}
