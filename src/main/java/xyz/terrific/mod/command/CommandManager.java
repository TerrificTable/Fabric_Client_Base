package xyz.terrific.mod.command;

import xyz.terrific.mod.Mod;
import xyz.terrific.mod.command.commands.SettingCommand;
import xyz.terrific.mod.command.commands.TestCommand;
import xyz.terrific.mod.command.commands.ToggleCommand;
import xyz.terrific.mod.event.Event;
import xyz.terrific.mod.event.events.EventPlayerSendChatMessage;
import xyz.terrific.mod.utils.ChatUtils;

public class CommandManager {

    /**
     * Init method, adds commands to commands list
     */
    public void init() {

        addCommand(new SettingCommand());
        addCommand(new ToggleCommand());
        addCommand(new TestCommand());

    }

    /**
     * Add Command to commands List
     * @param command Command to add
     */
    public void addCommand(Command command) {
        Mod.commands.add(command);
    }

    /**
     * Call Command
     * @param event Event... will be checked later... returns if it isn't a PlayerSendChatMessage Event
     */
    public static void callCommand(Event event) {
        if (!(event instanceof EventPlayerSendChatMessage)) return; // return if event isnt PlayerSendChatMessage Event
        if (((EventPlayerSendChatMessage) event).getMessage().startsWith(Mod.variables.PREFIX)) { // check if message starts with mod prefix
            event.setCancelled(true); // cancel event & sending message
            String message = ((EventPlayerSendChatMessage) event).getMessage().replace(Mod.variables.PREFIX, ""); // get message without prefix
            String command = message.split(" ")[0]; // get first word of message aka commandName

            // Iterate over commands and check if command is found
            for (Command c : Mod.commands) {
                // check if commandName is commands name or aliases contain the commandName
                if (c.getName().equalsIgnoreCase(command) || c.getAliases().contains(command)) {
                    c.executeCommand(message); // execute the command
                    return; // return because command was found
                }
            }
            // if command wasn't found
            ChatUtils.addChatMessage("Command '" + command + "' not found!");
        }

    }

    /**
     * get Commabd by index in commands List
     * @param index index of command
     * @return Command object
     */
    public Command getCommandByIndex(int index) {
        return Mod.commands.get(index);
    }

    /**
     * get Command by Name
     * @param name String name of command
     * @return Command Object or null
     */
    public static Command getCommandByName(String name) {
        for (Command command : Mod.commands) {
            if (command.getName().equals(name)) {
                return command;
            }
        }
        return null;
    }

    /**
     * get Command by Alias
     * @param alias String alias of command
     * @return Command Object or null
     */
    public static Command getCommandByAlias(String alias) {
        for (Command command : Mod.commands) {
            if (command.aliases.contains(alias)) return command;
        }
        return null;
    }

    /**
     * get Command by Alias or by Name
     * @param search Name or Alias
     * @return Command Object or null
     */
    public static Command getCommandByAliasOrName(String search) {
        Command res;
        res = getCommandByName(search);
        if (res != null) return res;
        res = getCommandByAlias(search);
        return res;
    }

}
