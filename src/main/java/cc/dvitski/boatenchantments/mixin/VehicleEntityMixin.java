package cc.dvitski.boatenchantments.mixin;

import cc.dvitski.boatenchantments.BoatExtras;
import cc.dvitski.boatenchantments.entity.BoatAccessor;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VehicleEntity.class)
public class VehicleEntityMixin {
    @WrapOperation(
            method = "destroy(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/Item;)V",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/ItemStack;"
            )
    )
    private ItemStack onBoatItem(ItemLike itemLike, Operation<ItemStack> original) {
        ItemStack originalStack = original.call(itemLike);

        if (this instanceof BoatAccessor accessor) {
            return BoatExtras.INSTANCE.modifyDroppedBoat(accessor.getItemStack(), originalStack);
        }

        return originalStack;
    }
}
