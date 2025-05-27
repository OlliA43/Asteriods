package dk.sdu.cbse.BulletSystem;

import dk.sdu.cbse.common.bullet.CreateBullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class bulletControlSystem implements IEntityProcessingService, CreateBullet {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * 4);
            bullet.setY(bullet.getY() + changeY * 4);

            if (bullet.getX() < 0) {
                world.removeEntity(bullet);
            }
            if (bullet.getY() < 0) {
                world.removeEntity(bullet);
            }
            if (bullet.getX() > gameData.getWidth()) {
                world.removeEntity(bullet);
            }
            if (bullet.getY() > gameData.getHeight()) {
                world.removeEntity(bullet);
            }
        }
    }

    @Override
    public Entity createBullet(Entity player, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setCoordinates(1,-1,1,1,-1,1,-1,-1);
        double changeX = Math.cos(Math.toRadians(player.getRotation()));
        double changeY = Math.sin(Math.toRadians(player.getRotation()));
        bullet.setX(player.getX() + changeX);
        bullet.setY(player.getY() + changeY);
        bullet.setRotation(player.getRotation());
        bullet.setRadius(1);
        bullet.setColor(0.0f,0.0f,0.0f,1.0f);
        return bullet;
    }
}
