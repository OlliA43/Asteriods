package dk.sdu.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();

    private double[] Coordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;

    private float[] color = {1.0f,1.0f,1.0f,1.0f};

    public String getID() {
        return ID.toString();
    }

    public double[] getCoordinates() {
        return Coordinates;
    }
    public void setCoordinates(double... coordinates) {
        Coordinates = coordinates;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRotation() {
        return rotation;
    }

    public float getRadius() {
        return radius;
    }

    public float[] getColor() {
        return color;
    }

    public void setColor(float r, float g, float b, float a) {
        this.color = new float[]{r,g,b,a};
    }
}
