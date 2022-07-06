package xyz.terrific.mod.helper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.Window;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;

// Credit: JexClient / @DustinRepo
public enum Wrapper {
    INSTANCE;

    /**
     * get Minecraft object
     * @return MinecraftClient object
     */
    public MinecraftClient getMinecraft() {
        return MinecraftClient.getInstance();
    }

    /**
     * Get local Player
     * @return ClientPlayerEntity
     */
    public ClientPlayerEntity getLocalPlayer() {
        return getMinecraft().player;
    }

    /**
     * Get Player (same as getLocalPlayer())
     * @return PlayerEntity
     */
    public PlayerEntity getPlayer() {
        return getLocalPlayer();
    }

    /**
     * get World
     * @return ClientWorld
     */
    public ClientWorld getWorld() {
        return getMinecraft().world;
    }

    /**
     * get Options...
     * @return GameOptions
     */
    public GameOptions getOptions() {
        return getMinecraft().options;
    }

    /**
     * get Client Player Interaction Manager
     * @return ClientPlayerInteractionManager
     */
    public ClientPlayerInteractionManager getClientPlayerInteractionManager() {
        return getMinecraft().interactionManager;
    }

    /**
     * Get Minecraft Window Object
     * @return Window
     */
    public Window getWindow() {
        return getMinecraft().getWindow();
    }

    /**
     * Get TextRenderer Object
     * @return TextRenderer
     */
    public TextRenderer getTextRenderer() {
        return getMinecraft().textRenderer;
    }

    /**
     * Get WorldRenderer Object
     * @return WorldRenderer
     */
    public WorldRenderer getWorldRenderer() {
        return getMinecraft().worldRenderer;
    }

    /**
     * Get GameRenderer Object
     * @return GameRenderer
     */
    public GameRenderer getGameRenderer() {
        return getMinecraft().gameRenderer;
    }

}
