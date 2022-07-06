package xyz.terrific.mod.module.modules.testing;

import net.minecraft.network.Packet;
import xyz.terrific.mod.event.Event;
import xyz.terrific.mod.event.events.EventPacket;
import xyz.terrific.mod.event.events.EventRender2D;
import xyz.terrific.mod.helper.FontHelper;
import xyz.terrific.mod.module.Module;
import xyz.terrific.mod.utils.ChatUtils;

public class PacketLog extends Module {

    public Packet<?> lastPacket;

    public PacketLog() {
        super("PacketLog", Category.TESTING);
    }

    public void onEvent(Event event) {
        if (!this.isToggled()) return;

        if (event instanceof EventPacket) {
            lastPacket = ((EventPacket) event).getPacket();
            ChatUtils.addChatMessage("Packet: " + lastPacket);
        }

        if (event instanceof EventRender2D) {
            FontHelper.INSTANCE.draw(((EventRender2D) event).getPoseStack(), "Packet: " + lastPacket, 10, 50, 0xffffff);
        }

    }
}
