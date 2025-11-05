package cc.dvitski.boatenchantments.data.server

import cc.dvitski.boatenchantments.enchantment.BoatEnchantments
import cc.dvitski.boatenchantments.tag.BoatEnchantmentsItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider
import net.minecraft.Util
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderSet
import net.minecraft.core.component.DataComponentMap
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.enchantment.Enchantment
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class EnchantmentProvider(output: FabricDataOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricCodecDataProvider<Enchantment>(output, future, Registries.ENCHANTMENT, Enchantment.DIRECT_CODEC) {
    override fun configure(consumer: BiConsumer<ResourceLocation, Enchantment>, provider: HolderLookup.Provider) {
        fun register(key: ResourceKey<Enchantment>,
                     enchantment: Enchantment.EnchantmentDefinition,
                     exclusiveSet: HolderSet<Enchantment> = HolderSet.direct(),
                     effects: DataComponentMap = DataComponentMap.EMPTY
        ) {
            val location = key.location()
            val description = Component.translatable(Util.makeDescriptionId("enchantment", location))
            consumer.accept(location, Enchantment(description, enchantment, exclusiveSet, effects))
        }

        val itemTags = provider.lookupOrThrow(Registries.ITEM)
        register(BoatEnchantments.TAILWIND, Enchantment.EnchantmentDefinition(
            itemTags.getOrThrow(BoatEnchantmentsItemTags.ENCHANTABLE_BOATS),
            Optional.empty(),
            1,
            2,
            Enchantment.dynamicCost(25, 25),
            Enchantment.dynamicCost(75, 25),
            8,
            emptyList(),
        ))
    }

    override fun getName(): String {
        return "Enchantments"
    }
}
