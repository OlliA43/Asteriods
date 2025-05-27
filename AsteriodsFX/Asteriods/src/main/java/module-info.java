import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteriods {
    requires Common;
    requires CommonAsteriod;
    provides IGamePluginService with dk.sdu.cbse.asteriod.AsteriodPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.asteriod.AsteriodProcessor;
}