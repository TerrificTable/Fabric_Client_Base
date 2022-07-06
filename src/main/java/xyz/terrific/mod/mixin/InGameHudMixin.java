package xyz.terrific.mod.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.terrific.mod.event.events.EventRender2D;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    /**
     * Draw the 2D overlay.
     * @param matrixStack the matrixStack I guess...
     * @param float_1 something?!
     * @param ci the callback info
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;isPressed()Z"))
    public void draw(MatrixStack matrixStack, float float_1, CallbackInfo ci) {
        try {
            // Create Render2D Event and call it
            new EventRender2D(matrixStack).run();
        } catch (Exception e) {
            // printStackTrace
            e.printStackTrace();
        }
    }

}
