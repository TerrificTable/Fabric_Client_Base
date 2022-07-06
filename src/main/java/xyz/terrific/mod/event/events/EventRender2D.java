package xyz.terrific.mod.event.events;

import net.minecraft.client.util.math.MatrixStack;
import xyz.terrific.mod.event.Event;

public class EventRender2D extends Event<EventRender2D> {

    private final MatrixStack poseStack; // matrix / pose Stack

    /**
     * Render2D Event constructor
     * @param poseStack matrix / pose Stack
     */
    public EventRender2D(MatrixStack poseStack) {
        this.poseStack = poseStack;
    }

    /**
     * get Matrix / Pose Stack
     * @return matrix / pose Stack
     */
    public MatrixStack getPoseStack() {
        return this.poseStack;
    }

}

