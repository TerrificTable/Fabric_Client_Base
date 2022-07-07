package xyz.terrific.mod.module.setting.settings;

import xyz.terrific.mod.module.setting.Setting;

public class BooleanSetting extends Setting {

    public boolean enabled;

    /**
     * Constructor
     * @param name Setting name
     * @param enabled Setting value
     */
    public BooleanSetting(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
        this.mode = "Boolean"; // set mode / type to "Boolean"
    }

    /**
     * is setting value true
     * @return setting value
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Set setting value
     * @param enabled new setting value
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Set setting value to opposite of current setting value
     */
    public void toggle() {
        this.enabled = !enabled;
    }
}
