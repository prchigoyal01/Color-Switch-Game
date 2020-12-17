package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.io.Serializable;

public final class Star extends GameObject {
    private transient Shape shape;

    Star(int X, int Y) {
        this.X = X;
        this.Y = Y;
        draw();
    }
    public Shape getShape() { return shape; }

    @Override
    public void draw() {

        Double x = (double) this.X;
        Double y = (double) this.Y;

        Rectangle r = new Rectangle(10, 10, Color.GOLD);
        r.setX(this.X);
        r.setY(this.Y);
        this.shape = r;
    }

    @Override
    public void motion() {}
}

