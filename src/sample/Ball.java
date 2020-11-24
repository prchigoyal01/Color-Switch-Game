package sample;

import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
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
    private ColorSwitch cs;
    private Star s;
    Group root;

    Ball(ColorSwitch cs, Star s, Group root) {
        this.X = 250;
        this.YBase = 600;
        this.Y = this.YBase;
        this.YMin = 180;
        this.color = Color.CYAN;
        this.cs = cs;
        this.s=s;
        this.root=root;

        draw();
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
        TranslateTransition tt = new TranslateTransition(Duration.millis(5000), this.shape);
        tt.setToY(-500);
        tt.setCycleCount(1);

        this.shape.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observableValue, Bounds bounds, Bounds t1) {
                if (((Path) Shape.intersect(shape, s.getShape())).getElements().size() > 0) {
                    tt.stop();
                    root.getChildren().remove(shape);
                }
                for(Shape x: cs.components) {
                    if (((Path) Shape.intersect(shape, x)).getElements().size() > 0) {
                        shape.setFill(Color.DEEPPINK);
                    }
                }
            }
        });
        tt.play();
    }

    private void mini_move_up() {}
    private void collides(){}
    private void destroy(){}
    private void updateScore(){}
}

