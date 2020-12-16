package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public final class Star extends GameObject{
    private Shape shape;

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
        Circle p = new Circle(this.X, this.Y, 10, Color.GOLD);
        this.shape = p;
    }

    @Override
    public void motion() {}

    public void disappear() {}
}

