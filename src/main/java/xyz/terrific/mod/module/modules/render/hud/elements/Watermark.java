package xyz.terrific.mod.module.modules.render.hud.elements;

import xyz.terrific.mod.Mod;
import xyz.terrific.mod.event.events.EventRender2D;
import xyz.terrific.mod.helper.FontHelper;
import xyz.terrific.mod.helper.Render2DHelper;
import xyz.terrific.mod.module.modules.render.hud.HudElement;
import xyz.terrific.mod.utils.ColorUtils;

import java.awt.*;

public class Watermark extends HudElement {
    /**
     * Hud Element Constructor
     */
    public Watermark() {
        super("NameAndVersionHud", new int[] { 10, 10 });
        this.showElement = true;
    }

    /**
     * Called when the Hud Element is rendered
     * @param event Render Event
     */
    @Override
    public void show(EventRender2D event) {
        // Draw Mod name and Mod Version on Hud
        String text = Mod.variables.MOD_NAME + " v." + Mod.variables.MOD_VERSION;
        Render2DHelper.INSTANCE.drawRect(getPosition()[0] - 5, getPosition()[1] - 5, getPosition()[0] + FontHelper.INSTANCE.getStringWidth(text) + 5, getPosition()[1] + FontHelper.INSTANCE.getStringHeight() + 5, new Color(0, 0, 0, 125));

        Color color = ColorUtils.rainbowNormal(1.0f, 255.0f, 255.0f);

        // Render2DHelper.INSTANCE.drawHLine(event.getPoseStack(), getPosition()[0] - 5, getPosition()[1] - 5, getPosition()[0] + FontHelper.INSTANCE.getStringWidth(text) + 5, color);
        // Render2DHelper.INSTANCE.drawHLine(event.getPoseStack(), getPosition()[0] - 5, getPosition()[1] + FontHelper.INSTANCE.getStringHeight() + 5, getPosition()[0] + FontHelper.INSTANCE.getStringWidth(text) + 5, color);

        FontHelper.INSTANCE.drawWithShadow(event.getPoseStack(), text, getPosition()[0], getPosition()[1], color.getRGB());
    }

}
