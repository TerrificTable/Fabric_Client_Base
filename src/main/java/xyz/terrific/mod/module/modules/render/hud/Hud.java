package xyz.terrific.mod.module.modules.render.hud;

import xyz.terrific.mod.event.Event;
import xyz.terrific.mod.event.events.EventRender2D;
import xyz.terrific.mod.module.Module;
import xyz.terrific.mod.module.modules.render.hud.elements.NameAndVersion;

import java.util.ArrayList;
import java.util.List;

public class Hud extends Module {

    // hudElements List
    public List<HudElement> hudElements = new ArrayList<>();


    /**
     * Hud (this is a module) Constructor
     */
    public Hud() {
        super("Hud", 0, Category.RENDER);

        // add hud element to hudElements List
        hudElements.add(new NameAndVersion());

    }

    /**
     * onEvent function
     * @param event Event object
     */
    public void onEvent(Event event) {
        if (!(event instanceof EventRender2D eventRender2D)) return; // return if event is not EventRender2D
        if (!this.isToggled()) return; // return if HudModule is not toggled

        // show every element in hudElements List
        for (HudElement elm : hudElements) { // iterate over elements in hudElements List
            elm.show(eventRender2D); // show element
        }

    }
}
