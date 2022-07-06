package xyz.terrific.mod.mixin;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.terrific.mod.event.events.EventWorldTick;

import javax.annotation.Nullable;

@Mixin(World.class)
public class WorldTickMixin {
    /**
     * Called every world tick
     * @param info The callback info
     */
    @Inject(at = @At("RETURN"), method="tickBlockEntities")
    protected void tickWorldAfterBlockEntity(@Nullable CallbackInfo info) {

        // Create and call WorldTickEvent
        new EventWorldTick().run();

    }
}
