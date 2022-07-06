package xyz.terrific.mod.event.events;

import net.minecraft.network.Packet;
import xyz.terrific.mod.event.Event;

// !!! NOT FINISHED !!!
public class EventPacket extends Event<EventPacket> {

    /**
     * Packet Event constructor
     * @param packet the packet
     * @param mode the mode
     */
    public EventPacket(Packet<?> packet, Mode mode) {  }

    /**
     * Mode Enum
     */
    public enum Mode {
        SEND,
        RECEIVE
    }
}
