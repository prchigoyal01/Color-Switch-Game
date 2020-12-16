package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public final class ColorSwitch extends GameObject{
    ArrayList<Shape> components;

    ColorSwitch(int X, int Y) {
        components = new ArrayList<Shape>();
        this.X = X;
        this.Y = Y;
        draw();
    }
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
        BL.setFill(Color.GOLD);
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
    public void motion(){}
}
