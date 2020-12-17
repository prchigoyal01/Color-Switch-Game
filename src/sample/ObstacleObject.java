package sample;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
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
        Line l1 = new Line(X,Y, X, Y + 100);
        l1.setStrokeWidth(20);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l1.setStroke(Color.YELLOW);
        movePivot(l1, 0, -50);

        Line l2 = new Line(X,Y,  X, Y - 100);
        l2.setStrokeWidth(20);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStroke(Color.CYAN);
        movePivot(l2, 0, 50);

        Line l3 = new Line(X,Y,X + 100, Y );
        l3.setStrokeWidth(20);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStroke(Color.PURPLE);
        movePivot(l3, -50, 0);

        Line l4 = new Line(X,Y, X - 100, Y);
        l4.setStrokeWidth(20);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStroke(Color.DEEPPINK);
        movePivot(l4, 50, 0);

        components.add(l1);
        components.add(l4);
        components.add(l2);
        components.add(l3);
    }

    @Override
    public void motion() {
        for(Shape x : components) {
            RotateTransition rt = new RotateTransition();
            rt.setDuration(Duration.millis(10000));
            rt.setNode(x);
            rt.setCycleCount(Animation.INDEFINITE);
            rt.setByAngle(360);
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
        Line l1 = new Line(X,Y, X, Y + 100);
        l1.setStrokeWidth(20);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l1.setStroke(Color.YELLOW);
        movePivot(l1, 0, -50);

        Line l2 = new Line(X,Y,  X, Y - 100);
        l2.setStrokeWidth(20);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStroke(Color.CYAN);
        movePivot(l2, 0, 50);

        Line l3 = new Line(X,Y,X + 100, Y );
        l3.setStrokeWidth(20);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStroke(Color.PURPLE);
        movePivot(l3, -50, 0);

        Line l4 = new Line(X,Y, X - 100, Y);
        l4.setStrokeWidth(20);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStroke(Color.DEEPPINK);
        movePivot(l4, 50, 0);

        components.add(l1);
        components.add(l4);
        components.add(l2);
        components.add(l3);
    }

    @Override
    public void motion() {
        for(Shape x : components) {
            RotateTransition rt = new RotateTransition();
            rt.setDuration(Duration.millis(10000));
            rt.setNode(x);
            rt.setCycleCount(Animation.INDEFINITE);
            rt.setByAngle(360);
            rt.play();
        }
    }
}

final class SquareLine extends ObstacleObject {
    SquareLine(int X, int Y) {
        this.X = X;
        this.Y = Y;
        draw();
        motion();
    }

    @Override
    public void draw() {
        Line l1 = new Line(X + 100,Y - 100, X + 100, Y + 100);
        l1.setStrokeWidth(20);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l1.setStroke(Color.CYAN);
        movePivot(l1, -100, 0);

        Line l2 = new Line(X + 100,Y + 100, X - 100, Y + 100);
        l2.setStrokeWidth(20);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStroke(Color.DEEPPINK);
        movePivot(l2, 0, -100);

        Line l3 = new Line(X - 100,Y + 100, X - 100, Y - 100);
        l3.setStrokeWidth(20);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStroke(Color.YELLOW);
        movePivot(l3, 100, 0);

        Line l4 = new Line(X - 100,Y - 100, X + 100, Y - 100);
        l4.setStrokeWidth(20);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStroke(Color.PURPLE);
        movePivot(l4, 0, 100);

        components.add(l1);
        components.add(l4);
        components.add(l2);
        components.add(l3);
    }

    @Override
    public void motion() {
        for(Shape x : components) {
            RotateTransition rt = new RotateTransition();
            rt.setDuration(Duration.millis(10000));
            rt.setNode(x);
            rt.setByAngle(360);
            rt.setCycleCount(Animation.INDEFINITE);
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
        Line l1 = new Line(0, Y, 125, Y);
        l1.setStrokeWidth(20);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l1.setStroke(Color.CYAN);

        Line l2 = new Line(125, Y, 250, Y);
        l2.setStrokeWidth(20);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStroke(Color.DEEPPINK);

        Line l3 = new Line(250, Y, 375, Y);
        l3.setStrokeWidth(20);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStroke(Color.YELLOW);

        Line l4 = new Line(375, Y, 500, Y);
        l4.setStrokeWidth(20);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStroke(Color.PURPLE);

        Line l5 = new Line(-125, Y, 0, Y);
        l5.setStrokeWidth(20);
        l5.setStrokeLineCap(StrokeLineCap.ROUND);
        l5.setStroke(Color.PURPLE);

        Line l6 = new Line(-250, Y, -125, Y);
        l6.setStrokeWidth(20);
        l6.setStrokeLineCap(StrokeLineCap.ROUND);
        l6.setStroke(Color.YELLOW);

        Line l7 = new Line(-375, Y, -250, Y);
        l7.setStrokeWidth(20);
        l7.setStrokeLineCap(StrokeLineCap.ROUND);
        l7.setStroke(Color.DEEPPINK);

        Line l8 = new Line(-500, Y, -375, Y);
        l8.setStrokeWidth(20);
        l8.setStrokeLineCap(StrokeLineCap.ROUND);
        l8.setStroke(Color.CYAN);

        components.add(l1);
        components.add(l4);
        components.add(l2);
        components.add(l3);
        components.add(l6);
        components.add(l5);
        components.add(l7);
        components.add(l8);
    }

    @Override
    public void motion() {
        for(Shape x : components) {
            TranslateTransition tt = new TranslateTransition();
            tt.setDuration(Duration.millis(10000));
            tt.setNode(x);
            tt.setCycleCount(Animation.INDEFINITE);
            tt.setByX(500);
            tt.setCycleCount(Animation.INDEFINITE);
            tt.play();
        }
    }
}

