package xyz.terrific.mod.utils;

import net.minecraft.text.Text;
import xyz.terrific.mod.Mod;
import xyz.terrific.mod.helper.Wrapper;

public class ChatUtils {

    /**
     * create function to add a message to chat (DOES NOT SEND MESSAGE, it only displays the message for the player)
     * @param message the message to add to chat
     */
    public static void addChatMessage(String message) {
        // Add `[MODNAME]  ` and message to chat
        Wrapper.INSTANCE.getMinecraft().inGameHud.getChatHud().addMessage(Text.of("[" + Mod.variables.MOD_NAME + "]  " + message));
    }

    /**
     * Send chat message
     * @param message the message to send to chat
     */
    public static void sendChatMessage(String message) {
        // Check if player is created... which means: check if player is playing
        if (Wrapper.INSTANCE.getMinecraft().player == null) return;

        // Send chat message
        Wrapper.INSTANCE.getMinecraft().player.sendChatMessage(message);
    }

}
