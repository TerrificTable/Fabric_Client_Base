package xyz.terrific.mod.mixin;

import xyz.terrific.mod.Mod;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class ExampleMixin {
	/**
	 * This is a mixin example.
	 * @param info CallbackInfo
	 */
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		// Log something using LOGGER object created in main (Mod.java) class
		Mod.LOGGER.info("This line is printed by an example mod mixin!");
	}
}
