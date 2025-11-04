package cc.dvitski.boatenchantments.data.server

import cc.dvitski.boatenchantments.tag.BoatEnchantmentsItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Item
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricTagProvider<Item>(output, Registries.ITEM, future) {
    override fun addTags(provider: HolderLookup.Provider) {
        builder(BoatEnchantmentsItemTags.ENCHANTABLE_BOATS)
            .forceAddTag(ItemTags.BOATS)
    }
}
