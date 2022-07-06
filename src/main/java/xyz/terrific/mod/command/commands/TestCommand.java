package xyz.terrific.mod.command.commands;

import xyz.terrific.mod.command.Command;
import xyz.terrific.mod.utils.ChatUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCommand extends Command {
    /**
     * Test Command Constructor
     */
    public TestCommand() {
        super("Test Command", new ArrayList<>(List.of( "test" )));
    }

    /**
     * executeCommand function... it will send some stuff like: arguments
     * @param command the Command that was executed
     */
    @Override
    public void executeCommand(String command) {
        ChatUtils.addChatMessage("Test Command Executed { " + command + " }");
        ChatUtils.addChatMessage("Arguments: " + Arrays.toString(command.split(" ")));
        ChatUtils.addChatMessage("Amount of arguments: " + (command.split(" ").length - 1));
    }
}
