package xyz.terrific.mod.command.commands;

import xyz.terrific.mod.Mod;
import xyz.terrific.mod.command.Command;
import xyz.terrific.mod.module.Module;
import xyz.terrific.mod.utils.ChatUtils;

import java.util.ArrayList;
import java.util.List;

public class ToggleCommand extends Command {
    /**
     * Toggle Command Constructor
     */
    public ToggleCommand() {
        super("toggle", new ArrayList<>(List.of( "toggle", "t" )));
        // Set command syntax
        this.syntax = Mod.variables.PREFIX + " toggle <module>";
    }

    /**
     * executeCommand function
     * @param command command String...
     */
    @Override
    public void executeCommand(String command) {
        // Get module by name
        Module module = Mod.moduleManager.getModuleByName(command.split(" ")[1]);
        if (module == null) { // check if module isn't null (aka if module was found)
            ChatUtils.addChatMessage("Module '" + command.split(" ")[1] + "' not found!"); // If module was not found send "error" message
            return;
        }
        module.toggle(); // Toggle module
        String status = "Off"; // Set status String to "Off"
        if (module.isToggled()) status = "On"; // change status String to "On" if module is toggled

        ChatUtils.addChatMessage("Module '" + command.split(" ")[1] + "' is now " + status); // send message containing module name and new state

    }
}
