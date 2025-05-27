package dk.sdu.cbse.EnemySystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;
    private double placementX = 50.0f;
    private double placementY = 50.0f;
    @Override
    public void start(GameData gameData, World world) {
        enemy = spawnEnemy(50,50);
        world.addEntity(enemy);
    }

    public Entity spawnEnemy(double placementX,double placementY) {
        Entity enemy = new Enemy();
        enemy.setCoordinates(-5,-5,10,0,-5,5);
        enemy.setX(placementX);
        enemy.setY(placementY);
        enemy.setColor(1.0f,0.0f,0.0f,1.0f);
        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            if (entity.getClass().equals(enemy.getClass())) {
                world.removeEntity(entity);
            }
        }
    }
}
