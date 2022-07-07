package xyz.terrific.mod.module.modules.render.hud;

import net.minecraft.client.util.Window;
import xyz.terrific.mod.event.events.EventRender2D;
import xyz.terrific.mod.helper.Wrapper;

public abstract class HudElement {

    public String name; // Hud Element name
    public String description; // Hud Element description
    // int[x, y]
    public int[] position; // x,y position of hud element
    public boolean showElement; // is element going to be shown

    // Overloaded Constructor...
    public HudElement(String name, String description, int[] position) {
        this.name = name;
        this.description = description;
        this.position = fixPos(position);
    }
    public HudElement(String name, int[] position) {
        this.name = name;
        this.description = "";
        this.position = fixPos(position);
    }
    public HudElement(String name) {
        this.name = name;
        this.description = "";
        this.position = new int[] { Wrapper.INSTANCE.getWindow().getWidth() / 2, Wrapper.INSTANCE.getWindow().getHeight() / 2 };
    }

    /**
     * abstract method to render hud element
     */
    public abstract void show(EventRender2D event);


    public Window getWindow() {
        return Wrapper.INSTANCE.getWindow();
    }
    public int getWidth() {
        return Wrapper.INSTANCE.getWindow().getWidth();
    }
    public int getHeight() {
        return Wrapper.INSTANCE.getWindow().getHeight();
    }

    /**
     * get Name of Hud Element
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set Hud Element Name
     * @param name new Element Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get Description of Hud Element
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set Hud Element Description
     * @param description new Element Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get Position of Hud Element
     * @return position
     */
    public int[] getPosition() {
        return position;
    }

    /**
     * set Hud Element Position
     * @param position new Element Position
     */
    public void setPosition(int[] position) {
        this.position = position;
    }

    /**
     * fix x position by setting it to 0 (this is useless)
     * @param pos position
     * @return int array
     */
    public int[] fixPos(int[] pos) {
        int[] res = new int[2];

        if (pos.length < 2) {
            res[0] = pos[0];
            res[1] = Wrapper.INSTANCE.getWindow().getHeight() / 2;
        }
        else {
            res[0] = pos[0];
            res[1] = pos[1];
        }

        return res;
    }
}
