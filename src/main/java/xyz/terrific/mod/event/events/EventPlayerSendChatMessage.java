package xyz.terrific.mod.event.events;

import xyz.terrific.mod.event.Event;

public class EventPlayerSendChatMessage extends Event<EventPlayerSendChatMessage> {

    public String message; // the message

    /**
     * PlayerSenChatMessage Event Constructor
     * @param message the message
     */
    public EventPlayerSendChatMessage(String message) {
        this.message = message; // set this.message to inputted message
    }

    /**
     * get Message
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}
