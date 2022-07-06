package xyz.terrific.mod.command;

import java.util.List;

public abstract class Command {

    public String name; // Command name
    public String description; // Command description
    public String syntax; // Command syntax
    public List<String> aliases; // Command aliases List


    // Overloaded Constructors...
    public Command(String name, String description, String syntax, List<String> aliases) {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
        this.aliases = aliases;
    }
    public Command(String name, String description, String syntax) {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
        this.aliases = null;
    }
    public Command(String name, String description, List<String> aliases) {
        this.name = name;
        this.description = description;
        this.syntax = "";
        this.aliases = aliases;
    }
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
        this.syntax = "";
        this.aliases = null;
    }
    public Command(String name, List<String> aliases) {
        this.name = name;
        this.description = "";
        this.syntax = "";
        this.aliases = aliases;
    }
    public Command(String name) {
        this.name = name;
        this.description = "";
        this.syntax = "";
        this.aliases = null;
    }


    /**
     * abstract execute Command function... forces classes that extent Command to implement `executeCommand` function
     * @param command
     */
    public abstract void executeCommand(String command);

    /**
     * get Command name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set Command Name
     * @param name new Command name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get Command description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set Command description
     * @param description new Command description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get Command syntax
     * @return syntax
     */
    public String getSyntax() {
        return syntax;
    }

    /**
     * set Command syntax
     * @param syntax new Command syntax
     */
    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    /**
     * get Command aliases
     * @return aliases
     */
    public List<String> getAliases() {
        return aliases;
    }

    /**
     * set Command aliases
     * @param aliases new Command aliases
     */
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }
}
