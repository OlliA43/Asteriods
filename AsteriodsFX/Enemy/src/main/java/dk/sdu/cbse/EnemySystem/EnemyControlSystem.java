package dk.sdu.cbse.EnemySystem;

import dk.sdu.cbse.common.bullet.CreateBullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.Vector;
import java.util.stream.Collectors;

public class EnemyControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            if(entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;
                if(shouldAttack(enemy, gameData)) {
                    attack(gameData,enemy, world);
                }
                move(enemy,world);
            }
        }
    }

    private boolean shouldAttack(final Enemy enemy, GameData gameData) {
        long currentTime = System.currentTimeMillis();

        float cooldownInMs = enemy.getAttackCooldown() * 1000;

        if (currentTime - enemy.getLastShotTime() > cooldownInMs) {
            return true;
        }
        return false;
    }

    private void move(final Enemy enemy, World world) {
        Entity player = findPlayer(world);
        if (player == null) return;

        double enemyX = enemy.getX();
        double enemyY = enemy.getY();
        double playerX = player.getX();
        double playerY = player.getY();

        double dirX = playerX - enemyX;
        double dirY = playerY - enemyY;

        double angle = Math.toDegrees(Math.atan2(dirY, dirX));

        enemy.setRotation(angle);

        double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
        double changeY = Math.sin(Math.toRadians(enemy.getRotation()));

        enemy.setX(enemyX + changeX * 0.80);
        enemy.setY(enemyY + changeY * 0.80);

    }

    public void attack(GameData gameData, final Enemy enemy, final World world) {

        enemy.setLastShotTime(System.currentTimeMillis());

        getCreateBullets().stream().findFirst().ifPresent(
                bullet -> {world.addEntity(bullet.createBullet(enemy, gameData));}
        );
    }

    private Entity findPlayer(World world) {
        for (Entity entity : world.getEntities()) {
            if (entity.getClass().getSimpleName().contains("Player")) {
                return entity;
            }
        }
        return null;
    }

    private Collection<? extends CreateBullet> getCreateBullets() {
        return ServiceLoader.load(CreateBullet.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
