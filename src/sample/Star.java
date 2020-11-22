package sample;

import javafx.scene.paint.Color;
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
        Polygon p = new Polygon();
        Double x = (double) this.X;
        Double y = (double) this.Y;
        Double[] arr = {x, y - 20, x + 20, y - 10, x + 15, y + 10, x - 15, y + 10, x - 20, y - 10};
        p.getPoints().addAll(arr);
        p.setFill(Color.GOLD);
        this.shape = p;
    }

    @Override
    public void motion() {}

    public void disappear() {}
}

