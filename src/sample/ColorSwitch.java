package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;

public final class ColorSwitch extends GameObject{
    ArrayList<Shape> components;
    private int YMove;
    private Group root;
    protected static TranslateTransition move = new TranslateTransition(Duration.millis(160));

    ColorSwitch(Group root) {
        components = new ArrayList<Shape>();
        this.X = 250;
        this.Y = -980;
        this.YMove = 0;
        this.root = root;
        draw();
    }

    public int getYMove() { return YMove; }
    public void setYMove(int YMove) { this.YMove = YMove; }

    @Override
    public void draw(){
        Arc UL = new Arc();
        UL.setCenterX(X);
        UL.setCenterY(Y);
        UL.setRadiusX(20);
        UL.setRadiusY(20);
        UL.setLength(90);
        UL.setStartAngle(90);
        UL.setFill(Color.BLUE);
        UL.setType(ArcType.ROUND);

        Arc BL = new Arc();
        BL.setCenterX(X);
        BL.setCenterY(Y);
        BL.setRadiusX(20);
        BL.setRadiusY(20);
        BL.setLength(90);
        BL.setStartAngle(180);
        BL.setFill(Color.BEIGE);
        BL.setType(ArcType.ROUND);

        Arc UR = new Arc();
        UR.setCenterX(X);
        UR.setCenterY(Y);
        UR.setRadiusX(20);
        UR.setRadiusY(20);
        UR.setLength(90);
        UR.setStartAngle(0);
        UR.setFill(Color.MAGENTA);
        UR.setType(ArcType.ROUND);

        Arc BR = new Arc();
        BR.setCenterX(X);
        BR.setCenterY(Y);
        BR.setRadiusX(20);
        BR.setRadiusY(20);
        BR.setLength(90);
        BR.setStartAngle(270);
        BR.setFill(Color.VIOLET);
        BR.setType(ArcType.ROUND);

        components.add(UL);
        components.add(UR);
        components.add(BL);
        components.add(BR);
    }

    @Override
    public void motion(){
        for(Shape x : components) {
            TranslateTransition tt = new TranslateTransition();
            tt.setDuration(Duration.millis(160));
            tt.setNode(x);
            tt.setByY(50);
            tt.play();
            if(x.getTranslateY() > 600) {
                x.setTranslateY(-980);
            }
        }
    }
    protected void destroy() {
        for(Shape x: components) {
            x.setTranslateY(Y - 200);
        }
    }
}
