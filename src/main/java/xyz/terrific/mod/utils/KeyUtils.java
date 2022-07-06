package xyz.terrific.mod.utils;

import java.awt.event.KeyEvent;
import java.util.Objects;

public class KeyUtils {

    /**
     * Returns the name of a key... takes a int
     * @param key The key to get the name of
     * @return The name of the key
     */
    public static String getKeyName(int key) {
        return KeyEvent.getKeyText(key);
    }

    /**
     * Get index of a key's name
     * @param key The key to get the index of
     * @return The index of the key
     */
    public static int getKeyIndex(String key) {
        for (int i=0; i < 1000000; i++) {
            if (Objects.equals(key, KeyEvent.getKeyText(i)))
                return i;
        }
        return -1;
    }

}

