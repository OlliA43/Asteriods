package dk.sdu.cbse.asteriod;

import dk.sdu.cbse.common.asteriod.Asteriod;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

public class AsteriodProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteriod : world.getEntities(Asteriod.class)) {
            // Calculate movement once
            double changeX = Math.cos(Math.toRadians(asteriod.getRotation())) * 0.5;
            double changeY = Math.sin(Math.toRadians(asteriod.getRotation())) * 0.5;


            double newX = asteriod.getX() + changeX;
            double newY = asteriod.getY() + changeY;

            int screenWidth = gameData.getWidth();
            int screenHeight = gameData.getHeight();

            if (newX < 0) {
                newX += screenWidth;
            } else if (newX > screenWidth) {
                newX -= screenWidth;
            }
            if (newY < 0) {
                newY += screenHeight;
            } else if (newY > screenHeight) {
                newY -= screenHeight;
            }

            asteriod.setX(newX);
            asteriod.setY(newY);

        }
    }
}
