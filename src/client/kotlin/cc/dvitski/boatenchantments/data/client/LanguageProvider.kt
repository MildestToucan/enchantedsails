package cc.dvitski.boatenchantments.data.client

import cc.dvitski.boatenchantments.enchantment.BoatEnchantments
import cc.dvitski.boatenchantments.tag.BoatEnchantmentsItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class LanguageProvider(output: FabricDataOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricLanguageProvider(output, future) {
    override fun generateTranslations(provider: HolderLookup.Provider, builder: TranslationBuilder) {
        builder.addEnchantment(BoatEnchantments.TAILWIND, "Tailwind")
        builder.addEnchantment(BoatEnchantments.CONTROL, "Control")

        builder.add(BoatEnchantmentsItemTags.ENCHANTABLE_BOATS, "Enchantable (Boats)")
    }
}
