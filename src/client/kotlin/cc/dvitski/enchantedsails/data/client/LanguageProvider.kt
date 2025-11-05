package cc.dvitski.enchantedsails.data.client

import cc.dvitski.enchantedsails.enchantment.ESBoatEnchantments
import cc.dvitski.enchantedsails.tag.ESItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class LanguageProvider(output: FabricDataOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricLanguageProvider(output, future) {
    override fun generateTranslations(provider: HolderLookup.Provider, builder: TranslationBuilder) {
        builder.addEnchantment(ESBoatEnchantments.TAILWIND, "Tailwind")
        builder.addEnchantment(ESBoatEnchantments.CONTROL, "Control")

        builder.add(ESItemTags.ENCHANTABLE_BOATS, "Enchantable (Boats)")
    }
}
