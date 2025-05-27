package dk.sdu.cbse.common.data;

import java.util.EnumMap;
import java.util.Map;

public class GameKeys {

    public enum Key {
        UP, LEFT, RIGHT,DOWN, SPACE
    }

    private final Map<Key, Boolean> keys = new EnumMap<>(Key.class);
    private final Map<Key, Boolean> previousKeys = new EnumMap<>(Key.class);

    public GameKeys() {
        // Initialize all keys to false
        for (Key key : Key.values()) {
            keys.put(key, false);
            previousKeys.put(key, false);
        }
    }

    public void update() {
        // Copy current key states to previous
        for (Key key : Key.values()) {
            previousKeys.put(key, keys.get(key));
        }
    }

    public void setKey(Key key, boolean pressed) {
        keys.put(key, pressed);
    }

    public boolean isDown(Key key) {
        return keys.get(key);
    }

    public boolean isPressed(Key key) {
        // A key is "pressed" if it's currently down but wasn't down in the previous update
        return keys.get(key) && !previousKeys.get(key);
    }
}