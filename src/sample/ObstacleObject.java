package sample;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public abstract class ObstacleObject extends GameObject{
    //all basic obstacles inherit ObstacleObject
    protected int speed;
    protected int direction;
    protected int orientation;
    protected ArrayList<Shape> components;
    private static Random rand = new Random();
    private static Color[] colors = new Color[]{Color.CYAN, Color.PURPLE, Color.DEEPPINK, Color.YELLOW};
    private ParallelTransition pt;
    protected ArrayList<Animation> transitions;

    ObstacleObject() {
        components = new ArrayList<Shape>();
        pt = new ParallelTransition();
        transitions = new ArrayList<>();
    }

    public ArrayList<Animation> getTransitions() { return transitions; }

    public ParallelTransition getPt() { return pt; }
    public void setPt(ParallelTransition pt) { this.pt = pt; }

    protected void movePivot(Node node, double x, double y){
        node.getTransforms().add(new Translate(-x, -y));
        node.setTranslateX(x); node.setTranslateY(y);
    }

    protected Color generateColor() {
        return colors[rand.nextInt(4)];
    }
}

final class LeftCross extends ObstacleObject {
    LeftCross(int X, int Y) {
        this.X = X;
        this.Y = Y;
        draw();
        motion();
    }
    @Override
    public void draw() {
        Line l1 = new Line(X,Y, X, Y + 50);
        l1.setStrokeWidth(20);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l1.setStroke(Color.YELLOW);
        movePivot(l1, 0, -25);

        Line l2 = new Line(X,Y,  X, Y - 50);
        l2.setStrokeWidth(20);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStroke(Color.CYAN);
        movePivot(l2, 0, 25);

        Line l3 = new Line(X,Y,X + 50, Y );
        l3.setStrokeWidth(20);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStroke(Color.PURPLE);
        movePivot(l3, -25, 0);

        Line l4 = new Line(X,Y, X - 50, Y);
        l4.setStrokeWidth(20);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStroke(Color.DEEPPINK);
        movePivot(l4, 25, 0);

        components.add(l1);
        components.add(l4);
        components.add(l2);
        components.add(l3);
    }

    @Override
    public void motion() {
        for(Shape x : components) {
            RotateTransition rt = new RotateTransition();
            rt.setDuration(Duration.millis(5000));
            rt.setNode(x);
            rt.setCycleCount(Animation.INDEFINITE);
            rt.setByAngle(360);
            rt.setAutoReverse(true);
            rt.play();
        }
    }
}

final class RightCross extends ObstacleObject {
    RightCross(int X, int Y) {
        this.X = X;
        this.Y = Y;
        draw();
        motion();
    }
    @Override
    public void draw() {
        Line l1 = new Line(X,Y, X, Y + 50);
        l1.setStrokeWidth(20);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l1.setStroke(Color.YELLOW);
        movePivot(l1, 0, -25);

        Line l2 = new Line(X,Y,  X, Y - 50);
        l2.setStrokeWidth(20);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStroke(Color.CYAN);
        movePivot(l2, 0, 25);

        Line l3 = new Line(X,Y,X + 50, Y );
        l3.setStrokeWidth(20);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStroke(Color.PURPLE);
        movePivot(l3, -25, 0);

        Line l4 = new Line(X,Y, X - 50, Y);
        l4.setStrokeWidth(20);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStroke(Color.DEEPPINK);
        movePivot(l4, 25, 0);

        components.add(l1);
        components.add(l4);
        components.add(l2);
        components.add(l3);
    }

    @Override
    public void motion() {
        for(Shape x : components) {
            RotateTransition rt = new RotateTransition();
            rt.setDuration(Duration.millis(5000));
            rt.setNode(x);
            rt.setCycleCount(Animation.INDEFINITE);
            rt.setByAngle(360);
            rt.setAutoReverse(false);
            rt.play();
        }
    }
}

final class SquareLine extends ObstacleObject {
    SquareLine() {
        draw();
        this.X = 250;
        this.Y = 220;
        motion();
    }

    @Override
    public void draw() {
        Line l1 = new Line(175,145, 325, 145);
        l1.setStrokeWidth(20);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l1.setStroke(Color.CYAN);
        movePivot(l1, 0, 75);

        Line l2 = new Line(175,295, 325, 295);
        l2.setStrokeWidth(20);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStroke(Color.DEEPPINK);
        movePivot(l2, 0, -75);

        Line l3 = new Line(175,145, 175, 295);
        l3.setStrokeWidth(20);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStroke(Color.YELLOW);
        movePivot(l3, 75, 0);

        Line l4 = new Line(325,145, 325, 295);
        l4.setStrokeWidth(20);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStroke(Color.PURPLE);
        movePivot(l4, -75, 0);

        components.add(l1);
        components.add(l4);
        components.add(l2);
        components.add(l3);
    }

    @Override
    public void motion() {
        for(Shape x : components) {
            RotateTransition rt = new RotateTransition();
            rt.setDuration(Duration.millis(5000));
            rt.setNode(x);
            rt.setByAngle(360);
            rt.setCycleCount(Animation.INDEFINITE);
            rt.setAutoReverse(false);
            rt.play();
        }
    }
}

final class DiamondLine extends ObstacleObject {
    DiamondLine(int X, int Y) {
        this.X = X;
        this.Y = Y;
        draw();
        motion();
    }

    @Override
    public void draw() {
        Line l1 = new Line(X,Y - 75, X + 75, Y);
        l1.setStrokeWidth(20);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l1.setStroke(Color.CYAN);
        movePivot(l1, -40, 40);

        Line l2 = new Line(X + 75, Y, X, Y + 75);
        l2.setStrokeWidth(20);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStroke(Color.DEEPPINK);
        movePivot(l2, -40, -40);

        Line l3 = new Line(X,Y + 75, X - 75, Y);
        l3.setStrokeWidth(20);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStroke(Color.YELLOW);
        movePivot(l3, 40, -40);

        Line l4 = new Line(X - 75,Y, X, Y - 75);
        l4.setStrokeWidth(20);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStroke(Color.PURPLE);
        movePivot(l4, 40, 40);

        components.add(l1);
        components.add(l4);
        components.add(l2);
        components.add(l3);
    }

    @Override
    public void motion() {
        for(Shape x : components) {
            RotateTransition rt = new RotateTransition();
            rt.setDuration(Duration.millis(5000));
            rt.setNode(x);
            rt.setCycleCount(Animation.INDEFINITE);
            rt.setByAngle(360);
            rt.setAutoReverse(false);
            rt.play();
        }
    }
}

