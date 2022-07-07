package xyz.terrific.mod;

// Imports
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.terrific.mod.command.Command;
import xyz.terrific.mod.command.CommandManager;
import xyz.terrific.mod.event.events.EventKeyPress;
import xyz.terrific.mod.module.Module;
import xyz.terrific.mod.module.ModuleManager;

import java.util.ArrayList;
import java.util.List;

// Fabric main class
public class Mod implements ModInitializer {

	// Create Fabric LOGGER Object
	public static final Logger LOGGER = LoggerFactory.getLogger("mod");
	// Create / initialize variables object
	public static final Variables variables = new Variables("Mod", "1.0.1", "1.19.0");
	// Create commandManager object / instance of CommandManager class
	public static CommandManager commandManager;
	// Create moduleManager object / instance of ModuleManager class
	public static ModuleManager moduleManager;

	// Create array / List of modules
	public static List<Module> modules = new ArrayList<>();
	// Create array / List of commands
	public static List<Command> commands = new ArrayList<>();


	/**
	 * This is the entry point of the mod.
	 */
	@Override
	public void onInitialize() {
		// Initiate commandManager object / instance of CommandManager class
		commandManager = new CommandManager();
		// Initiate moduleManager object / instance of ModuleManager class
		moduleManager = new ModuleManager();

		// Add commands to commandManager
		commandManager.init();
		// Add modules to moduleManager
		moduleManager.init();


		// Log that the mod has been initialized (With name and version)
		LOGGER.info(String.format("%s V.%s Initialized!", variables.MOD_NAME, variables.MOD_VERSION));
	}


	// Is executed if any key is pressed, takes EventKeyPress input
	public static void onKeyPress(EventKeyPress event) {
		// Iterate over modules
		for (Module module : modules) {
			// Check if modules bind keycode is equal to the keycode of the pressed key
			if (module.getKeyCode() == event.getKeyCode())
				module.toggle(); // Toggle module
		}
	}
}
