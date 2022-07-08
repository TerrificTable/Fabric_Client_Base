package xyz.terrific.mod;

public class Variables {

    // ModID String
    public String MOD_ID             = "mod";
    // ModName
    public String MOD_NAME           = "Mod";
    // ModVersion
    public String MOD_VERSION;
    // ModAuthor
    public String MOD_AUTHOR         = "TerrificTable";
    // Minecraft Version (it's only for information about the client, it does not actually change / set the minecraft version)
    public String MOD_MC_VERSION;

    public String PREFIX = ">";

    // Constructor for Variable class
    public Variables(String modName, String modVersion, String mcVersion) {
        this.MOD_NAME = modName; // "Change" modname
        this.MOD_VERSION = modVersion; // "Change" modversion
        this.MOD_MC_VERSION = mcVersion; // "Change" minecraft version (this does not actually change the minecraft version, it's just for some information in the client)
    }

}
