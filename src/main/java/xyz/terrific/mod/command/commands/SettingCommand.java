package xyz.terrific.mod.command.commands;

import xyz.terrific.mod.Mod;
import xyz.terrific.mod.command.Command;
import xyz.terrific.mod.module.Module;
import xyz.terrific.mod.module.setting.Setting;
import xyz.terrific.mod.module.setting.settings.BooleanSetting;
import xyz.terrific.mod.module.setting.settings.ModeSetting;
import xyz.terrific.mod.module.setting.settings.NumberSetting;
import xyz.terrific.mod.utils.ChatUtils;

import java.util.ArrayList;
import java.util.List;

public class SettingCommand extends Command {
    public SettingCommand() {
        super("SettingCommand", new ArrayList<>(List.of( "setting", "s" )));
        this.setSyntax(Mod.variables.PREFIX + "setting <module> <setting name | settings> <new value>");
    }

    @Override
    public void executeCommand(String command) {

        if (command.split(" ").length == 2 || command.split(" ")[2].equalsIgnoreCase("settings")) {
            Module module = Mod.moduleManager.getModuleByName(command.split(" ")[1]);
            StringBuilder settings = new StringBuilder();
            for (Setting setting : module.settings) settings.append(setting.name + ", ");

            ChatUtils.addChatMessage("Module '" + module.getName() + "''s settings: " + settings);
            return;
        }

        if (command.split(" ").length < 3) {
            ChatUtils.addChatMessage(this.getSyntax());
            return;
        }

        Module module = Mod.moduleManager.getModuleByName(command.split(" ")[1]);
        if (module == null) { // check if module isn't null (aka if module was found)
            ChatUtils.addChatMessage("Module '" + command.split(" ")[1] + "' not found!"); // If module was not found send "error" message
            return;
        }

        Setting setting = module.getSettingByName(command.split(" ")[2]);
        if (setting == null) {
            ChatUtils.addChatMessage("Setting '" + command.split(" ")[2] + "' not found");
            return;
        }

        String newValue;
        try {
            newValue = command.split(" ")[3];
        } catch (Exception ignored) {
            ChatUtils.addChatMessage(Mod.variables.PREFIX + "setting " + module.getName() + " " + setting.name + " <new value | (if mode setting) modes>");
            return;
        }
        if (setting instanceof BooleanSetting) {
            boolean bool = false;
            if (newValue.equalsIgnoreCase("true") || newValue.equalsIgnoreCase("on")) bool = true;
            ((BooleanSetting) setting).setEnabled(bool);
        } else if (setting instanceof NumberSetting) {
            try {
                try {
                    ((NumberSetting) setting).setValue(Double.parseDouble(newValue));
                } catch (Exception ignored) {
                    ((NumberSetting) setting).setValue(Integer.parseInt(newValue));
                }
            } catch (Exception ignored) {
                ChatUtils.addChatMessage("'" + newValue + "' can't be parsed as integer or double");
                return;
            }
        } else if (setting instanceof ModeSetting) {
            if (newValue.equalsIgnoreCase("modes")) {
                StringBuilder modes = new StringBuilder();
                for (String mode : ((ModeSetting) setting).modes) modes.append(mode + ", ");

                ChatUtils.addChatMessage("Modes for Setting '" + command.split(" ")[1] + "/" + command.split(" ")[2] + "' are: " + modes);
                return;
            }
            ((ModeSetting) setting).setMode(newValue);
        } else {
            ChatUtils.addChatMessage("Setting '" + setting.name + "' is " + setting.mode + " Setting");
            return;
        }


        ChatUtils.addChatMessage("New value for Setting '" + command.split(" ")[1] + "/" + command.split(" ")[2] + "' is now '" + newValue + "'");

    }
}
