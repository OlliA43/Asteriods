
package dk.sdu.cbse.app;


import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;

import java.util.Collection;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class App extends Application {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();

    public static void main(String[] args) {
        launch(App.class);
    }

    @Override
    public void start(Stage window) {
        gameWindow.setPrefSize(gameData.getWidth(), gameData.getHeight());

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                gameData.getKeys().setKey(GameKeys.Key.LEFT, true);
            }
            if (event.getCode() == KeyCode.RIGHT) {
               gameData.getKeys().setKey(GameKeys.Key.RIGHT, true);
            }
            if (event.getCode() == KeyCode.UP) {
             gameData.getKeys().setKey(GameKeys.Key.UP, true);
            }
            if (event.getCode() == KeyCode.SPACE) {
                gameData.getKeys().setKey(GameKeys.Key.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                gameData.getKeys().setKey(GameKeys.Key.LEFT, false);
            }
            if (event.getCode() == KeyCode.RIGHT) {
                gameData.getKeys().setKey(GameKeys.Key.RIGHT, false);
            }
            if (event.getCode() == KeyCode.UP) {
                gameData.getKeys().setKey(GameKeys.Key.UP, false);
            }
            if (event.getCode() == KeyCode.SPACE) {
                gameData.getKeys().setKey(GameKeys.Key.SPACE, false);
            }
        });

        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }
        render();
        window.setScene(scene);
        window.show();
    }

    private void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }
        }.start();
    }

    private void update() {
        for (IEntityProcessingService entityProcessingService : getEntityProcessingServices()) {
            entityProcessingService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessingService : getPostEntityProcessingServices()) {
            postEntityProcessingService.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity polygonEntity : polygons.keySet()) {
            if(!world.getEntities().contains(polygonEntity)) {
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removedPolygon);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());

            float[] entityColor = entity.getColor();
            if(entityColor != null) {
                javafx.scene.paint.Color color = javafx.scene.paint.Color.color(
                        entityColor[0],
                        entityColor[1],
                        entityColor[2],
                        entityColor[3]
                );
                polygon.setFill(color);
            }
        }
    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

}
