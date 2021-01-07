package pl.fox.neuralsnake.world;

import java.awt.*;

public class Snake {

    private float x;
    private float y;

    public Snake(){

    }

    public void update(){

    }

    public void render(Graphics2D g){
        g.setColor(Color.BLUE);
        g.drawRect(10, 10, 10, 10);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
