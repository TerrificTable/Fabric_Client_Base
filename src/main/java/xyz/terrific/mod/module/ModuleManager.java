package xyz.terrific.mod.module;

import xyz.terrific.mod.Mod;
import xyz.terrific.mod.module.modules.render.hud.Hud;
import xyz.terrific.mod.module.modules.testing.PacketLog;
import xyz.terrific.mod.module.modules.testing.TestModule;

public class ModuleManager {

    /**
     * List of all modules
     */
    public void init() {

        /* {  Testing  } */
        addModule(new TestModule());
        addModule(new PacketLog());

        /* {  Combat  } */


        /* {  Misc  } */


        /* {  Render  } */
        addModule(new Hud());


        /* {  Movement  } */


        /* {  Client  } */


    }

    /**
     * add Module to modules list
     * @param module Module to be added
     */
    private void addModule(Module module) {
        Mod.modules.add(module);
    }

    /**
     * Get module by index in modules array
     * @param index Index of module in modules array
     * @return Module at index
     */
    public Module getModuleByIndex(int index) {
        return Mod.modules.get(index);
    }

    /**
     * Get module by name
     * @param name Name of module
     * @return Module that matches the name
     */
    public Module getModuleByName(String name) {
        for (Module module : Mod.modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }
        return null;
    }

}
