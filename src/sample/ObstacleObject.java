package sample;

import javafx.animation.Animation;
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

public abstract class ObstacleObject extends GameObject{
    //all basic obstacles inherit ObstacleObject
    protected int speed;
    protected int direction;
    protected int orientation;
    protected ArrayList<Shape> components;

    ObstacleObject() {
        components = new ArrayList<Shape>();
    }

    protected void movePivot(Node node, double x, double y){
        node.getTransforms().add(new Translate(-x, -y));
        node.setTranslateX(x); node.setTranslateY(y);
    }
}

final class ringSmall extends ObstacleObject {
    ringSmall() {
        draw();
        this.X = 250;
        this.Y = 220;
        motion();
    }
    @Override
    public void draw() {
        Arc UL = new Arc();
        UL.setCenterX(250);
        UL.setCenterY(220);
        UL.setRadiusX(80);
        UL.setRadiusY(80);
        UL.setLength(90);
        UL.setStartAngle(90);
        UL.setFill(Color.CYAN);
        movePivot(UL, 40, 40);

        Arc BL = new Arc();
        BL.setCenterX(250);
        BL.setCenterY(220);
        BL.setRadiusX(80);
        BL.setRadiusY(80);
        BL.setLength(90);
        BL.setStartAngle(180);
        BL.setFill(Color.YELLOW);
        movePivot(BL, 40, -40);

        Arc UR = new Arc();
        UR.setCenterX(250);
        UR.setCenterY(220);
        UR.setRadiusX(80);
        UR.setRadiusY(80);
        UR.setLength(90);
        UR.setStartAngle(0);
        UR.setFill(Color.DEEPPINK);
        movePivot(UR, -40, 40);

        Arc BR = new Arc();
        BR.setCenterX(250);
        BR.setCenterY(220);
        BR.setRadiusX(80);
        BR.setRadiusY(80);
        BR.setLength(90);
        BR.setStartAngle(270);
        BR.setFill(Color.PURPLE);
        movePivot(BR, -40, -40);

        components.add(UL);
        components.add(UR);
        components.add(BL);
        components.add(BR);
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

final class ringMedium extends ObstacleObject {
    ringMedium() {
        draw();
        this.X = 250;
        this.Y = 220;
        motion();
    }
    @Override
    public void draw() {
        Arc UL = new Arc();
        UL.setCenterX(250);
        UL.setCenterY(220);
        UL.setRadiusX(125);
        UL.setRadiusY(125);
        UL.setLength(90);
        UL.setStartAngle(90);
        UL.setFill(Color.CYAN);
        movePivot(UL, 60, 60);

        Arc BL = new Arc();
        BL.setCenterX(250);
        BL.setCenterY(220);
        BL.setRadiusX(125);
        BL.setRadiusY(125);
        BL.setLength(90);
        BL.setStartAngle(180);
        BL.setFill(Color.YELLOW);
        movePivot(BL, 60, -60);

        Arc UR = new Arc();
        UR.setCenterX(250);
        UR.setCenterY(220);
        UR.setRadiusX(125);
        UR.setRadiusY(125);
        UR.setLength(90);
        UR.setStartAngle(0);
        UR.setFill(Color.DEEPPINK);
        movePivot(UR, -60, 60);

        Arc BR = new Arc();
        BR.setCenterX(250);
        BR.setCenterY(220);
        BR.setRadiusX(125);
        BR.setRadiusY(125);
        BR.setLength(90);
        BR.setStartAngle(270);
        BR.setFill(Color.PURPLE);
        movePivot(BR, -60, -60);

        components.add(UL);
        components.add(UR);
        components.add(BL);
        components.add(BR);
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

final class ringLarge extends ObstacleObject {
    ringLarge() {
        draw();
        this.X = 250;
        this.Y = 220;
        motion();
    }
    @Override
    public void draw() {
        Arc UL = new Arc();
        UL.setCenterX(250);
        UL.setCenterY(220);
        UL.setRadiusX(190);
        UL.setRadiusY(190);
        UL.setLength(90);
        UL.setStartAngle(90);
        UL.setFill(Color.CYAN);
        movePivot(UL, 95, 95);

        Arc BL = new Arc();
        BL.setCenterX(250);
        BL.setCenterY(220);
        BL.setRadiusX(190);
        BL.setRadiusY(190);
        BL.setLength(90);
        BL.setStartAngle(180);
        BL.setFill(Color.YELLOW);
        movePivot(BL, 95, -95);

        Arc UR = new Arc();
        UR.setCenterX(250);
        UR.setCenterY(220);
        UR.setRadiusX(190);
        UR.setRadiusY(190);
        UR.setLength(90);
        UR.setStartAngle(0);
        UR.setFill(Color.DEEPPINK);
        movePivot(UR, -95, 95);

        Arc BR = new Arc();
        BR.setCenterX(250);
        BR.setCenterY(220);
        BR.setRadiusX(190);
        BR.setRadiusY(190);
        BR.setLength(90);
        BR.setStartAngle(270);
        BR.setFill(Color.PURPLE);
        movePivot(BR, -95, -95);

        components.add(UL);
        components.add(UR);
        components.add(BL);
        components.add(BR);
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

final class LeftCross extends ObstacleObject {
    LeftCross() {
        draw();
        this.X = 250;
        this.Y = 220;
        motion();
    }
    @Override
    public void draw() {
        Line l1 = new Line(200,220, 125, 145);
        l1.setStrokeWidth(20);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l1.setStroke(Color.CYAN);
        movePivot(l1, 35, 35);

        Line l2 = new Line(200,220, 275, 145);
        l2.setStrokeWidth(20);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStroke(Color.YELLOW);
        movePivot(l2, -35, 35);

        Line l3 = new Line(200,220, 275, 295);
        l3.setStrokeWidth(20);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStroke(Color.DEEPPINK);
        movePivot(l3, -35, -35);

        Line l4 = new Line(200,220, 125, 295);
        l4.setStrokeWidth(20);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStroke(Color.PURPLE);
        movePivot(l4, 35, -35);

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
    RightCross() {
        draw();
        this.X = 250;
        this.Y = 220;
        motion();
    }
    @Override
    public void draw() {
        Line l1 = new Line(300,220, 225, 145);
        l1.setStrokeWidth(20);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l1.setStroke(Color.YELLOW);
        movePivot(l1, 35, 35);

        Line l2 = new Line(300,220, 375, 145);
        l2.setStrokeWidth(20);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStroke(Color.CYAN);
        movePivot(l2, -35, 35);

        Line l3 = new Line(300,220, 375, 295);
        l3.setStrokeWidth(20);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStroke(Color.PURPLE);
        movePivot(l3, -35, -35);

        Line l4 = new Line(300,220, 225, 295);
        l4.setStrokeWidth(20);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStroke(Color.DEEPPINK);
        movePivot(l4, 35, -35);

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
            rt.setCycleCount(Animation.INDEFINITE);
            rt.setByAngle(360);
            rt.setAutoReverse(false);
            rt.play();
        }
    }
}

final class DiamondLine extends ObstacleObject {
    DiamondLine() {
        draw();
        this.X = 250;
        this.Y = 220;
        motion();
    }

    @Override
    public void draw() {
        Line l1 = new Line(250,145, 325, 220);
        l1.setStrokeWidth(20);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l1.setStroke(Color.CYAN);
        movePivot(l1, -40, 40);

        Line l2 = new Line(325,220, 250, 295);
        l2.setStrokeWidth(20);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStroke(Color.DEEPPINK);
        movePivot(l2, -40, -40);

        Line l3 = new Line(250,295, 175, 220);
        l3.setStrokeWidth(20);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStroke(Color.YELLOW);
        movePivot(l3, 40, -40);

        Line l4 = new Line(175,220, 250, 145);
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



