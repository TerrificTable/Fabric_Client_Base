package xyz.terrific.mod.module.modules.testing;

import xyz.terrific.mod.event.Event;
import xyz.terrific.mod.event.events.EventRender2D;
import xyz.terrific.mod.helper.FontHelper;
import xyz.terrific.mod.module.Module;

import java.awt.event.KeyEvent;

public class TestModule extends Module {
    /**
     * Test Module Constructor
     */
    public TestModule() {
        super("TestModule", "This is a test module", KeyEvent.VK_X, Category.TESTING);
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
    }

}
