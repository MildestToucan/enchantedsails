package cc.dvitski.boatenchantments.mixin.client;

import cc.dvitski.boatenchantments.client.ClientBoatExtras;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoatRenderer.class)
public abstract class BoatRendererMixin {
    @Shadow
    @Final
    private EntityModel<BoatRenderState> model;

    @Inject(method = "submitTypeAdditions", at = @At("TAIL"))
    private void onSubmitTypeAdditions(BoatRenderState state, PoseStack stack, SubmitNodeCollector collector, int light, CallbackInfo ci) {
        ClientBoatExtras.INSTANCE.renderGlint(this.model, state, stack, collector, light);
    }
}
