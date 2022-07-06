package xyz.terrific.mod.event;

import xyz.terrific.mod.Mod;
import xyz.terrific.mod.command.CommandManager;
import xyz.terrific.mod.module.Module;

public class Event<T> {

    public boolean cancelled; // Event cancelled state
    public EventType type; // Event type
    public EventDirection direction; // Event direction

    /**
     * Run / Call Event
     * @return executed Event
     */
    public Event<T> run() {
        if (cancelled)
            return null;
        // call Command...
        CommandManager.callCommand(this);

        // call Module's onEvent
        for (Module module : Mod.modules) {
            if (module.isToggled())
                module.onEvent(this);
        }
        return this; // return current Event
    }

    /**
     * Is Event cancelled
     * @return true if Event is cancelled
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Set Event cancelled state
     * @param cancelled new Event cancelled state
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * get Event Type
     * @return Event Type
     */
    public EventType getType() {
        return type;
    }

    /**
     * set Event Type
     * @param type new Event Type
     */
    public void setType(EventType type) {
        this.type = type;
    }

    /**
     * get Event Direction
     * @return Event Direction
     */
    public EventDirection getDirection() {
        return direction;
    }

    /**
     * set Event Direction
     * @param direction new Event Direction
     */
    public void setDirection(EventDirection direction) {
        this.direction = direction;
    }

    /**
     * is Event Type Pre?
     * @return true if Event is Pre
     */
    public boolean isPre() {
        return (type != null) && (type == EventType.PRE);
    }

    /**
     * is Event Type Post?
     * @return true if Event is Post
     */
    public boolean isPost() {
        return (type != null) && (type == EventType.POST);
    }

    /**
     * is Event Direction in?
     * @return true if event direction is in
     */
    public boolean directionIn() {
        return (direction != null) && (direction == EventDirection.IN);
    }

    /**
     * is Event Direction out?
     * @return true if event direction is out
     */
    public boolean directionOut() {
        return (direction != null) && (direction == EventDirection.OUT);
    }
}
