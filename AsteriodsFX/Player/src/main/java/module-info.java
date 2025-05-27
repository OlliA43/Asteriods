
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Player {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.cbse.common.bullet.CreateBullet;
    provides IGamePluginService with dk.sdu.cbse.PlayerSystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.PlayerSystem.PlayerControlSystem;
}