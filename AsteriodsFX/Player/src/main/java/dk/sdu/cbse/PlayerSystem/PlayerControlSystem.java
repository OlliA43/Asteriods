package dk.sdu.cbse.PlayerSystem;

import dk.sdu.cbse.common.bullet.CreateBullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class PlayerControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        for(Entity player : world.getEntities(Player.class)) {
            if (gameData.getKeys().isDown(GameKeys.Key.LEFT)) {
                player.setRotation(player.getRotation() - 5);
            }
            if (gameData.getKeys().isDown(GameKeys.Key.RIGHT)) {
                player.setRotation(player.getRotation() + 5);
            }
            if (gameData.getKeys().isDown(GameKeys.Key.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + changeX * 1.1);
                player.setY(player.getY() + changeY * 1.1);
            }
            if (gameData.getKeys().isDown(GameKeys.Key.SPACE)) {
                getCreateBullets().stream().findFirst().ifPresent(
                        bullet -> {world.addEntity(bullet.createBullet(player, gameData));}
                        );
            }
            if (player.getX() < 0) {
                player.setX(1);
            }
            if (player.getY() < 0) {
                player.setY(1);
            }
            if (player.getX() > gameData.getWidth()) {
                player.setX(gameData.getWidth() - 1);
            }
            if (player.getY() > gameData.getHeight()) {
                player.setY(gameData.getHeight() - 1);
            }
        }

    }

    private Collection<? extends CreateBullet> getCreateBullets() {
        return ServiceLoader.load(CreateBullet.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
