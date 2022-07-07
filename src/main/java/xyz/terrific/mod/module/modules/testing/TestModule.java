package xyz.terrific.mod.module.modules.testing;

import xyz.terrific.mod.event.Event;
import xyz.terrific.mod.event.events.EventRender2D;
import xyz.terrific.mod.helper.FontHelper;
import xyz.terrific.mod.module.Module;
import xyz.terrific.mod.module.setting.settings.BooleanSetting;
import xyz.terrific.mod.module.setting.settings.ModeSetting;
import xyz.terrific.mod.module.setting.settings.NumberSetting;

import java.awt.event.KeyEvent;

public class TestModule extends Module {

    public BooleanSetting booleanSetting = new BooleanSetting("BooleanSetting", true);
    public ModeSetting modeSetting = new ModeSetting("ModeSetting", "Test1", "Test", "Test1", "Test2", "Test3");
    public NumberSetting numberSetting = new NumberSetting("NumberSetting", 10, 0, 100, 1);

    /**
     * Test Module Constructor
     */
    public TestModule() {
        super("TestModule", "This is a test module", KeyEvent.VK_X, Category.TESTING);
        this.addSettings(booleanSetting, modeSetting, numberSetting);
    }

    /**
     * onEnable function... this will print "TestModule onEnable()" to the console
     */
    @Override
    public void onEnable() {
        System.out.println("TestModule onEnable()");
    }

    /**
     * onEvent function...
     * @param event Event object
     */
    @Override
    public void onEvent(Event event) {
        // return if event is not EventRender2D
        if (!(event instanceof EventRender2D)) return;

        // Draw "Test Module" to Hud
        FontHelper.INSTANCE.draw(((EventRender2D) event).getPoseStack(), "Test Module", 10, 10, 0xffffff);
        FontHelper.INSTANCE.draw(((EventRender2D) event).getPoseStack(), "BooleanSetting: " + booleanSetting.isEnabled(), 10, 15 + FontHelper.INSTANCE.getStringHeight(), 0xffffff);
        FontHelper.INSTANCE.draw(((EventRender2D) event).getPoseStack(), "ModeSetting: " + modeSetting.getMode(), 10, 25 + FontHelper.INSTANCE.getStringHeight(), 0xffffff);
        FontHelper.INSTANCE.draw(((EventRender2D) event).getPoseStack(), "NumberSetting: " + numberSetting.getValue(), 10, 35 + FontHelper.INSTANCE.getStringHeight(), 0xffffff);

    }

}
