package sample;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;

public abstract class ObstacleCombination extends GameObject{
    protected Star star;
    protected int YMove;
    protected transient ArrayList<Shape> components;
    protected transient Group root;
    ObstacleCombination(Group root) {
        this.YMove = 50;
        this.root = root;
        this.components = new ArrayList<>();
    }

    protected void destroy() {
        for(Shape x: components) {
            if(x.getTranslateY() >= 1800) {
                TranslateTransition tt1 = new TranslateTransition();
                tt1.setDuration(Duration.millis(1));
                tt1.setNode(x);
                tt1.setByY(-2500);
                tt1.play();
            }
        }
    }
    //Combinations inherit ObstacleCombination and are made with union of various obstacle objects and a star
}


final class SquareLineStar extends ObstacleCombination {
    SquareLine square;
    SquareLineStar(Group root) {
        super(root);
        this.X = 250;
        this.Y = -1000;
        draw();
    }
    @Override
    public void draw() {
        square = new SquareLine(X, Y);
        star = new Star(X, Y);
        components.addAll(square.components);
        components.add(star.getShape());
    }

    @Override
    public void motion() {
        for(Shape x : components) {
            TranslateTransition tt = new TranslateTransition();
            tt.setDuration(Duration.millis(160));
            tt.setNode(x);
            tt.setByY(50);
            tt.play();
        }
    }
}

final class DiamondLineStar extends ObstacleCombination {
    DiamondLine diamond;
    DiamondLineStar(Group root) {
        super(root);
        this.X = 250;
        this.Y = 220;
        draw();
    }
    @Override
    public void draw() {
        diamond = new DiamondLine(X, Y);
        star = new Star(X, Y - 50);
        components.addAll(diamond.components);
        components.add(star.getShape());
    }

    @Override
    public void motion() {
        for(Shape x : components) {
            TranslateTransition tt = new TranslateTransition();
            tt.setDuration(Duration.millis(160));
            tt.setNode(x);
            tt.setByY(50);
            tt.play();
        }
    }
}

final class LeftCrossStar extends ObstacleCombination {
    LeftCross cross;
    LeftCrossStar(Group root) {
        super(root);
        this.X = 150;
        this.Y = -300;
        draw();
    }
    @Override
    public void draw() {
        cross = new LeftCross(X, Y);
        star = new Star(X + 100, Y);
        components.addAll(cross.components);
        components.add(star.getShape());
    }

    @Override
    public void motion() {
        for(Shape x : components) {
            TranslateTransition tt = new TranslateTransition();
            tt.setDuration(Duration.millis(160));
            tt.setNode(x);
            tt.setByY(50);
            tt.play();
        }
    }
}

final class RightCrossStar extends ObstacleCombination {
    RightCross cross;
    RightCrossStar(Group root) {
        super(root);
        this.X = 350;
        this.Y = -600;
        draw();
    }
    @Override
    public void draw() {
        cross = new RightCross(X, Y);
        star = new Star(X - 100, Y);
        components.addAll(cross.components);
        components.add(star.getShape());
    }

    @Override
    public void motion() {
        for(Shape x : components) {
            TranslateTransition tt = new TranslateTransition();
            tt.setDuration(Duration.millis(160));
            tt.setNode(x);
            tt.setByY(50);
            tt.play();
        }
    }
}