package dk.sdu.cbse.BulletSystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class bulletPlugin implements IGamePluginService {

    private Entity bullet;
    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            if (entity.getClass().equals(bullet.getClass())) {
                world.removeEntity(entity);
            }
        }
    }
}
