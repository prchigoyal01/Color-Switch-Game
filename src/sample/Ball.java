package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

import java.io.Serializable;
import javafx.scene.Group;
import javafx.util.Duration;

public final class Ball extends GameObject {
    private Shape shape;
    private int score;
    private int YMin;
    private int YBase;
    private Color color;
    private TranslateTransition tt;
    Group root;

    Ball(Group root) {
        this.X = 250;
        this.YBase = 600;
        this.Y = 600;
        this.YMin = 500;
        this.color = Color.CYAN;
        this.root=root;

        draw();
        this.tt = new TranslateTransition(Duration.millis(1000), this.shape);
        motion();
    }

    public Shape getShape() { return shape; }

    @Override
    public void draw() {
        Circle c = new Circle(this.X, this.Y, 10, this.color);
        this.shape = c;
    }

    @Override
    public void motion() {
        new AnimationTimer() {
            long time = System.currentTimeMillis();

            @Override
            public void handle(long l) {

            }
        }.start();
    }

    public void mini_move_up() {
        this.Y -= 100;
        tt.setToY(this.Y);
        System.out.println("clicked");
        System.out.println(this.Y);
        tt.play();
    }
    private void collides(){}
    private void destroy(){}
    private void updateScore(){}
}