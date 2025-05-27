package dk.sdu.cbse.common.data;

public class GameData {
    private int width = 900;
    private int height = 900;
    private final GameKeys keys = new GameKeys();

    public GameKeys getKeys() {
        return keys;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
