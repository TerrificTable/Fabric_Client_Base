package xyz.terrific.mod.module.modules.render.hud.elements;

import xyz.terrific.mod.Mod;
import xyz.terrific.mod.event.events.EventRender2D;
import xyz.terrific.mod.helper.FontHelper;
import xyz.terrific.mod.module.modules.render.hud.HudElement;

public class NameAndVersion extends HudElement {
    /**
     * Hud Element Constructor
     */
    public NameAndVersion() {
        super("NameAndVersionHud", new int[] { 10, 10 });
    }

    /**
     * Called when the Hud Element is rendered
     * @param event Render Event
     */
    @Override
    public void show(EventRender2D event) {
        // Draw Mod name and Mod Version on Hud
        FontHelper.INSTANCE.drawWithShadow(event.getPoseStack(), Mod.variables.MOD_NAME + " V." + Mod.variables.MOD_VERSION, getPosition()[0], getPosition()[1], 0xFFFFFF);
    }

}
