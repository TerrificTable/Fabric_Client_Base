package xyz.terrific.mod.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.terrific.mod.event.events.EventPacket;

@Mixin(ClientPlayNetworkHandler.class)
public class OnPacketMixin {
    /**
     * send packet thing... it's not used anywhere for now...
     * @param packet The packet...
     * @param ci the callback info
     */
    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    public void sendPacketPre(Packet<?> packet, CallbackInfo ci) {
        // Create PacketSend Event and call it
        EventPacket eventPacketSent = (EventPacket) new EventPacket(packet, EventPacket.Mode.SEND).run();

        // cancel packet being sent if event is canceled
        if (eventPacketSent.isCancelled())
            ci.cancel();
    }
}

