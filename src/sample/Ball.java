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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.Group;
import javafx.util.Duration;

import javax.net.ssl.SNIHostName;

public final class Ball extends GameObject {
    private Shape shape;
    private int score;
    private int YMin;
    private int YBase;
    private transient Color color;
    private transient Group root;
    protected transient TranslateTransition moveDown;
    protected transient TranslateTransition moveUp;
    private transient static Random rand = new Random();
    private transient Color[] colors;
    private ArrayList<Bounds> scored;
    private ArrayList<Bounds> obstructed;

    Ball(Group root) {
        this.X = 250;
        this.YBase = 0;
        this.Y = 600;
        this.YMin = 500;
        this.color = Color.CYAN;
        this.root = root;
        this.colors = new Color[]{Color.CYAN, Color.PURPLE, Color.DEEPPINK, Color.YELLOW};
        this.scored = new ArrayList<>();
        this.obstructed = new ArrayList<>();

        draw();
        moveDown = new TranslateTransition(Duration.millis(1600), this.shape);
        moveUp = new TranslateTransition(Duration.millis(160), this.shape);
    }

    public Shape getShape() { return shape; }
    public int getYMin() { return YMin; }
    public int getYBase() { return YBase; }
    public int getScore() { return score; }

    @Override
    public void draw() {
        this.color = colors[rand.nextInt(4)];
        Circle c = new Circle(this.X, this.Y, 10, this.color);
        this.shape = c;
    }

    @Override
    public void motion() {
        moveDown.setToY(YBase);
        moveDown.play();
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        collides();
    }

    public void mini_move_up() {
        moveUp.setByY(-25);
        moveUp.play();
    }
    public void collides(){
        Iterator itr = this.root.getChildren().iterator();
        boolean destroyBall = false;
        while(itr.hasNext()) {
            Node x = (Node) itr.next();
            if(x instanceof Shape && !(x instanceof Circle) && x.getBoundsInParent().intersects(shape.getBoundsInParent())) {
                if(intersectsObstacle((Shape) x)) {
                    boolean collided = false;
                    for(Bounds b : obstructed) {
                        collided = b.intersects(x.getBoundsInParent()) || collided;
                    }
                    if(!collided) {
                        System.out.println("Collides with obstacle ->" + x.getBoundsInParent());
                        destroyBall = true;
                        obstructed.add(x.getBoundsInParent());
                    }
                }
                if(intersectsStar((Shape) x)) {
                    boolean collided = false;
                    for(Bounds b : scored) {
                        collided = b.intersects(x.getBoundsInParent()) || collided;
                    }
                    if(!collided) {
                        updateScore();
                        System.out.println("Collides with star");
                        scored.add(x.getBoundsInParent());
                    }
                }
                if(intersectsColorSwitch((Shape) x)) {
                    System.out.println("Collides with color switch");
                    changeColor();
                }
            }
        }
        if(destroyBall) {
            destroy();
        }
    }
    private boolean intersectsObstacle(Shape x) {
        Color c = null;
        if(x.getFill() == null) {
            if (x.getStroke().toString().equals("0xff1493ff")) {
                c = Color.DEEPPINK;
            } else if (x.getStroke().toString().equals("0x800080ff")) {
                c = Color.PURPLE;
            } else if (x.getStroke().toString().equals("0xffff00ff")) {
                c = Color.YELLOW;
            } else if (x.getStroke().toString().equals("0x00ffffff")) {
                c = Color.CYAN;
            }
        }
        else {
            if (x.getFill().toString().equals("0xff1493ff")) {
                c = Color.DEEPPINK;
            } else if (x.getFill().toString().equals("0x800080ff")) {
                c = Color.PURPLE;
            } else if (x.getFill().toString().equals("0xffff00ff")) {
                c = Color.YELLOW;
            } else if (x.getFill().toString().equals("0x00ffffff")) {
                c = Color.CYAN;
            }
        }
        return c != null && c != Color.GOLD && c != Color.BEIGE && c != Color.MAGENTA && c != Color.VIOLET && c != Color.BLUE && c != color;
    }
    private boolean intersectsStar(Shape x) {
        return x.getFill() == Color.GOLD;
    }
    private boolean intersectsColorSwitch(Shape x) {
        Paint c = x.getFill();
        return c == Color.BEIGE || c == Color.MAGENTA || c == Color.VIOLET || c == Color.BLUE;
    }
    private void destroy(){
        root.getChildren().remove(shape);
        SelectPlayer.currentUser.setLevelsPlayed(SelectPlayer.currentUser.getLevelsPlayed() + 1);
    }
    private void updateScore(){
        score++;
        SelectPlayer.currentUser.setStarsBalance(SelectPlayer.currentUser.getStarsBalance()+ 1);
    }
    private void changeColor() {
        this.color = colors[rand.nextInt(4)];
        this.shape.setFill(this.color);
    }
}