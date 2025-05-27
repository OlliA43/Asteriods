package dk.sdu.cbse.PlayerSystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private Entity playerEntity;
    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        playerEntity = spawnPlayer(gameData);
        world.addEntity(playerEntity);
    }

    private Entity spawnPlayer(GameData gameData) {
        Entity player = new Player();
        player.setCoordinates(-5,-5,10,0,-5,5);
        player.setX(gameData.getWidth()/2);
        player.setY(gameData.getHeight()/2);
        player.setColor(0.0f,0.0f,1.0f,1.0f);
        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(playerEntity);
    }
}
