package cc.dvitski.boatenchantments.mixin;

import cc.dvitski.boatenchantments.BoatEnchantmentsMod;
import cc.dvitski.boatenchantments.BoatExtras;
import cc.dvitski.boatenchantments.entity.BoatAccessor;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.AbstractBoat;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBoat.class)
public abstract class AbstractBoatMixin extends VehicleEntity implements BoatAccessor {
    @Unique
    private static String ITEM_STACK_KEY = ResourceLocation.fromNamespaceAndPath(BoatEnchantmentsMod.MOD_ID, "stack").toString();

    @Nullable
    @Unique
    private ItemStack itemStack;

    @Unique
    private boolean clientGlint = false;

    private AbstractBoatMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @WrapMethod(method = "getPickResult")
    private ItemStack onPickItem(Operation<ItemStack> original) {
        return BoatExtras.INSTANCE.modifyDroppedBoat(this.getItemStack(), original.call());
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void onSave(ValueOutput values, CallbackInfo ci) {
        ItemStack stack = this.getItemStack();
        if (stack != null) {
            values.store(ITEM_STACK_KEY, ItemStack.CODEC, stack);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void onRead(ValueInput values, CallbackInfo ci) {
        values.read(ITEM_STACK_KEY, ItemStack.CODEC).ifPresent(this::setItemStack);
    }

    @Unique
    @Override
    public @Nullable ItemStack getItemStack() {
        return this.itemStack;
    }

    @Unique
    @Override
    public void setItemStack(@Nullable ItemStack stack) {
        if (stack == null) {
            this.itemStack = null;
            BoatExtras.INSTANCE.sendGlintPayload(this, false);
        } else {
            this.itemStack = stack.copy();
            BoatExtras.INSTANCE.sendGlintPayload(this, stack.isEnchanted());
        }
    }

    @Unique
    @Override
    public boolean getClientGlint() {
        return this.clientGlint;
    }

    @Unique
    @Override
    public void setClientGlint(boolean glint) {
        this.clientGlint = glint;
    }
}
