package xyz.terrific.mod.module;

import xyz.terrific.mod.event.Event;
import xyz.terrific.mod.module.setting.Setting;

public class Module {

    public String name; // Name of module
    public String description; // Module description
    public int keyCode; // Index of key
    public String displayName; // Modules display name (not used currently)
    public Category category; // Module category
    public boolean toggled; // Module toggled state

    public Setting[] settings;

    // Overloaded Constructor... means: you can create a new module not having to specify all the variables
    public Module(String name, String displayName, String description, int keyCode, Category category, boolean toggled) {
        this.name = name;
        this.description = description;
        this.keyCode = keyCode;
        this.displayName = displayName;
        this.category = category;
        this.toggled = toggled;
    }
    public Module(String name, String description, int keyCode, Category category, boolean toggled) {
        this(name, name, description, keyCode, category, toggled);
    }
    public Module(String name, String description, int keyCode, Category category) {
        this(name, name, description, keyCode, category, false);
    }
    public Module(String name, String description, Category category) {
        this(name, name, description, 0, category, false);
    }
    public Module(String name, int keyCode, Category category) {
        this(name, name, "", keyCode, category, false);
    }
    public Module(String name, Category category) {
        this(name, name, "", 0, category, false);
    }


    /**
     * onEnable function, is called when module is enabled
     */
    public void onEnable() {  }
    /**
     * onDisable function, is called when module is disabled
     */
    public void onDisable() {  }
    /**
     * onEvent function, is called if a registered event is fired
     * @param event Event object
     */
    public void onEvent(Event event) {  }


    /**
     * Get module name
     * @return Module name
     */
    public String getName() {
        return name;
    }

    /**
     * set Module name
     * @param name new Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get module description
     * @return Module description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set Module description
     * @param description new Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get module key code
     * @return Module key code
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * set Key Code or key index
     * @param keyCode new Key Code or key index
     */
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * Get module display name
     * @return Module display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * set Module display name
     * @param displayName new Display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Get module category
     * @return Module category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * set Module category
     * @param category new Category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Get module toggled state
     * @return Module toggled state
     */
    public boolean isToggled() {
        return toggled;
    }

    /**
     * set Module toggled state
     * @param toggled new Toggled state
     */
    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    /**
     * Toggle module state
     */
    public void toggle() {
        this.toggled = !this.toggled;
    }

    /**
     * add Settings to module
     * @param settings Settings to add
     */
    public void addSettings(Setting... settings) {
        this.settings = settings;
    }

    /**
     * get all settings
     * @return Array of Settings (Setting[])
     */
    public Setting[] getSettings() {
        return this.settings;
    }

    /**
     * get setting by name
     * @param name name of setting
     * @return requested Setting
     */
    public Setting getSettingByName(String name) {
        for (Setting setting : this.settings) {
            if (setting.name.equalsIgnoreCase(name)) {
                return setting;
            }
        }
        return null;
    }


    /**
     * Module category enum
     */
    public enum Category {
        TESTING("Testing"),
        COMBAT("Combat"),
        MISC("Misc"),
        RENDER("Render"),
        MOVEMENT("Movement"),
        CLIENT("Client");

        public String name;

        /**
         * Category constructor
         * @param name Name of category
         */
        Category(String name) {
            this.name = name;
        }
    }
}
