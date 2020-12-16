package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Group;
import javafx.util.Duration;

import javax.net.ssl.SNIHostName;

public final class Ball extends GameObject {
    private transient Shape shape;
    private int score;
    private int YMin;
    private int YBase;
    private transient Color color;
    private transient Group root;
    private transient Scene scene;
    protected transient TranslateTransition moveDown;
    protected transient TranslateTransition moveUp;
    private transient static Random rand = new Random();
    private transient Color[] colors;

    Ball(Group root, Scene scene) {
        this.X = 250;
        this.YBase = 0;
        this.Y = 500;
        this.YMin = 500;
        this.color = Color.CYAN;
        this.root = root;
        this.scene = scene;
        this.colors = new Color[]{Color.CYAN, Color.PURPLE, Color.DEEPPINK, Color.YELLOW};

        draw();
        motion();
    }

    public Shape getShape() { return shape; }

    @Override
    public void draw() {
        this.color = colors[rand.nextInt(4)];
        Circle c = new Circle(this.X, this.Y, 10, this.color);
        this.shape = c;
    }

    @Override
    public void motion() {
        this.moveUp = new TranslateTransition(Duration.millis(160), shape);
        moveDown = new TranslateTransition(Duration.millis(1000), shape);
        //this.pt = new ParallelTransition(moveDown, moveUp);
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveDown.setToY(YBase);
                moveDown.play();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                collides();
            }
        }.start();
    }

    public void mini_move_up() {
    }
    private void collides(){
        Bounds bound = this.shape.getBoundsInParent();
        for(Node x : this.root.getChildren()) {
            if(x instanceof Shape && this.shape.intersects(x.getBoundsInParent())) {

            }
        }
    }

    private void destroy(){}
    private void updateScore(){}
}