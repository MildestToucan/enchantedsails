package cc.dvitski.boatenchantments.mixin.client;

import cc.dvitski.boatenchantments.client.ClientBoatExtras;
import net.minecraft.client.renderer.entity.AbstractBoatRenderer;
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import net.minecraft.world.entity.vehicle.AbstractBoat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBoatRenderer.class)
public class AbstractBoatRendererMixin {
    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/vehicle/AbstractBoat;Lnet/minecraft/client/renderer/entity/state/BoatRenderState;F)V", at = @At("TAIL"))
    private void onExtractRenderState(AbstractBoat entity, BoatRenderState state, float tickDelta, CallbackInfo ci) {
        ClientBoatExtras.INSTANCE.extractRenderState(entity, state);
    }
}
