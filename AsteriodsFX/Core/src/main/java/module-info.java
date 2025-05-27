module Core {
    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
    requires javafx.controls;
    requires javafx.graphics;
    requires Common;

    exports dk.sdu.cbse.app;
}