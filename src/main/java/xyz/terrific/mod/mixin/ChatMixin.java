package xyz.terrific.mod.mixin;


import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.message.ChatMessageSigner;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.terrific.mod.event.events.EventPlayerSendChatMessage;

@Mixin(ClientPlayerEntity.class)
public class ChatMixin {

    /**
     * sendChatMessage Mixin
     * @param messageSigner who sent the message, I guess?
     * @param string the message
     * @param component idk?!
     * @param ci CallbackInfo
     */
    @Inject(method = "sendChatMessagePacket", at = @At("HEAD"), cancellable = true)
    public void sendChatMessage(ChatMessageSigner messageSigner, String string, Text component, CallbackInfo ci) {

        // Create PlayerSendChatMessage Event and call it
        EventPlayerSendChatMessage eventSendMessage = (EventPlayerSendChatMessage) new EventPlayerSendChatMessage(string).run();

        // If the event is cancelled, the message will not be sent.
        if (eventSendMessage.isCancelled()) {
            ci.cancel();
        }
    }

}
