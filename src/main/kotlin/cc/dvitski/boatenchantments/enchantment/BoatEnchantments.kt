package cc.dvitski.boatenchantments.enchantment

import cc.dvitski.boatenchantments.BoatEnchantmentsMod
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.enchantment.Enchantment

object BoatEnchantments {
    val TAILWIND = register("tailwind")

    private fun register(id: String): ResourceKey<Enchantment> {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(BoatEnchantmentsMod.MOD_ID, id))
    }
}
