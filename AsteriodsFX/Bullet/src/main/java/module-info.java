import dk.sdu.cbse.BulletSystem.bulletControlSystem;
import dk.sdu.cbse.common.bullet.CreateBullet;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Bullet {
    requires Common;
    requires CommonBullet;

    provides IEntityProcessingService with dk.sdu.cbse.BulletSystem.bulletControlSystem;
    provides IGamePluginService with dk.sdu.cbse.BulletSystem.bulletPlugin;
    provides CreateBullet with dk.sdu.cbse.BulletSystem.bulletControlSystem;

}