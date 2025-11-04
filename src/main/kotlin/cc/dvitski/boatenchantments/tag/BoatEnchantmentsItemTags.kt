package cc.dvitski.boatenchantments.tag

import cc.dvitski.boatenchantments.BoatEnchantmentsMod
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

object BoatEnchantmentsItemTags {
    val ENCHANTABLE_BOATS = create("enchantable/boats")

    private fun create(id: String): TagKey<Item> {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(BoatEnchantmentsMod.MOD_ID, id))
    }
}
