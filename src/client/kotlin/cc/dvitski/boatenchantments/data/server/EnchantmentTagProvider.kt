package cc.dvitski.boatenchantments.data.server

import cc.dvitski.boatenchantments.enchantment.BoatEnchantments
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.tags.EnchantmentTags
import net.minecraft.world.item.enchantment.Enchantment
import java.util.concurrent.CompletableFuture

class EnchantmentTagProvider(output: FabricDataOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricTagProvider<Enchantment>(output, Registries.ENCHANTMENT, future) {
    override fun addTags(provider: HolderLookup.Provider) {
        builder(EnchantmentTags.IN_ENCHANTING_TABLE)
            .addOptional(BoatEnchantments.TAILWIND)
    }
}
