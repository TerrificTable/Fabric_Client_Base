package xyz.terrific.mod.event.events;

import xyz.terrific.mod.event.Event;
import xyz.terrific.mod.utils.KeyUtils;

public class EventKeyPress extends Event<EventKeyPress> {
    public int keyCode; // KeyCode

    /**
     * Constructor
     * @param keyCode Integer Key Code
     */
    public EventKeyPress(int keyCode) {
        this.keyCode = keyCode; // set this.keyCode to inputted keyCode
    }

    /**
     * get Key Code
     * @return Integer key Code
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * set Key Code
     * @param keyCode new Key Code
     */
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * get Key Value... returns character / key name
     * @return String key value
     */
    public String getKeyVal() { return KeyUtils.getKeyName(this.keyCode); }
}
