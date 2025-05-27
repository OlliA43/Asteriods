package dk.sdu.cbse.asteriod;

import dk.sdu.cbse.common.asteriod.Asteriod;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import java.util.Random;

public class AsteriodPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        Entity asteriod = createAsteriod(gameData);
        world.addEntity(asteriod);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Asteriod.class)) {
            world.removeEntity(entity);
        }
    }

    private Entity createAsteriod(GameData gameData) {
        Entity asteroid = new Asteriod();
        Random random = new Random();
        int size = random.nextInt(25) + 5;
        asteroid.setRadius(size);
        asteroid.setCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(10);
        asteroid.setY(10);
        asteroid.setRotation(random.nextInt(90));
        return asteroid;
    }
}
