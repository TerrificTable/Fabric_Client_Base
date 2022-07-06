package xyz.terrific.mod.mixin;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.terrific.mod.Mod;
import xyz.terrific.mod.event.EventDirection;
import xyz.terrific.mod.event.EventType;
import xyz.terrific.mod.event.events.EventKeyPress;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    /**
     * is executed every time a key is pressed
     * @param window the "window" (clickgui, multiplayer menu, etc) that was open when the key was pressed
     * @param key the key(Code) that was pressed
     * @param scancode no clue
     * @param action idk
     * @param modifiers the modifier key... like in a key combination: CTRL + C (CTRL is the modifier key)
     * @param info the callback info
     */
    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo info) {
        // Create KeyPress Event
        EventKeyPress event = new EventKeyPress(key);
        event.setType(EventType.PRE); // Set event type
        event.setDirection(EventDirection.IN); // set event direction
        event.run(); // call event

        Mod.onKeyPress(event); // this will go through all mods and toggle the mods that have the same keybind as the pressed key

        if (event.isCancelled()) info.cancel(); // cancel key press if event is cancelled
    }

    /**
     * no clue... but it works
     * @param window the "window" (clickgui, multiplayer menu, etc) that was open when the key was pressed
     * @param codePoint ???
     * @param modifiers the modifier key... like in a key combination: CTRL + C (CTRL is the modifier key)
     * @param info the callback info
     */
    @Inject(method = "onChar", at = @At("HEAD"), cancellable = true)
    private void onChar(long window, int codePoint, int modifiers, CallbackInfo info) {
        // Create KeyPress Event
        EventKeyPress event = new EventKeyPress(codePoint);
        event.setType(EventType.PRE); // Set event type
        event.setDirection(EventDirection.IN); // set event direction
        event.run(); // call event

        Mod.onKeyPress(event); // this will go through all mods and toggle the mods that have the same keybind as the pressed key

        if (event.isCancelled()) info.cancel(); // cancel key press if event is cancelled
    }
}

