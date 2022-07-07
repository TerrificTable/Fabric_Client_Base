package xyz.terrific.mod.module.setting.settings;

import xyz.terrific.mod.module.setting.Setting;

import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting {

    public int index;
    public List<String> modes;

    /**
     * Constructor
     * @param name Setting name
     * @param defaultMode setting default mode
     * @param modes all setting modes
     */
    public ModeSetting(String name, String defaultMode, String... modes) {
        this.name = name;
        this.modes = Arrays.asList(modes);
        this.index = this.modes.indexOf(defaultMode);
        this.mode = "Modes";
    }

    /**
     * Returns the current Mode
     * @return current Mode (String)
     */
    public String getMode() {
        return modes.get(index);
    }

    /**
     * Set mode
     * @param mode new mode
     */
    public void setMode(String mode) {
        index = modes.indexOf(mode);
    }

    /**
     * is input mode current mode
     * @param mode mode to check
     * @return boolean
     */
    public boolean is(String mode) {
        return index == modes.indexOf(mode);
    }

    /**
     * Cycle over modes
     */
    public void cycle() {
        if (index < modes.size() - 1)
            index++;
        else index = 0;
    }
}
