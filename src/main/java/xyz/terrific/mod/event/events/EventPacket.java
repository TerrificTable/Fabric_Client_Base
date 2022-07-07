package xyz.terrific.mod.event.events;

import net.minecraft.network.Packet;
import xyz.terrific.mod.event.Event;

public class EventPacket extends Event<EventPacket> {

    public Packet<?> packet;
    public Mode mode;
    public boolean cancelled;

    /**
     * Packet Event constructor
     * @param packet the packet
     * @param mode the mode
     */
    public EventPacket(Packet<?> packet, Mode mode) {
        this.packet = packet;
        this.mode = mode;
    }

    /**
     * get Packet Object
     * @return packet Object
     */
    public Packet<?> getPacket() {
        return packet;
    }

    /**
     * get Mode
     * @return mode
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * get cancelled state
     */
    public void getCancelled() {
        cancelled = true;
    }

    /**
     * set cancelled state
     * @param cancelled new Event cancelled state
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Mode Enum
     */
    public enum Mode {
        SEND,
        RECEIVE
    }
}
