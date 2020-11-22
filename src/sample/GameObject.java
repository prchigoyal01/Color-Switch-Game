package sample;

import javafx.scene.shape.Shape;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class GameObject implements Serializable{
    protected int X;
    protected int Y;
    protected final static long SerialVersionUID = 42L;


    public abstract void draw();
    public abstract void motion();

    private void serializable(){}
    private void deserializable(){}
}

