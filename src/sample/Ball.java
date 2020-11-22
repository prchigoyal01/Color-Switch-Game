package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.io.Serializable;

public final class Ball extends GameObject {
    private Shape shape;
    private int score;
    private int YMin;
    private int YBase;
    private Color color;

    Ball() {
        this.X = 250;
        this.YBase = 600;
        this.Y = this.YBase;
        this.YMin = 180;
        this.color = Color.CYAN;

        draw();
    }

    public Shape getShape() { return shape; }

    @Override
    public void draw() {
        Circle c = new Circle(this.X, this.Y, 10, this.color);
        this.shape = c;
    }

    @Override
    public void motion() {}

    private void mini_move_up() {}
    private void collides(){}
    private void destroy(){}
    private void updateScore(){}
}

