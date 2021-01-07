package pl.fox.neuralsnake.world;

import java.awt.*;

public class Snake {

    private static final int INITIAL_LENGTH = 3;

    private int[] x;
    private int[] y;
    private int length;
    private int score;

    private boolean isUp, isDown, isLeft, isRight;
    private boolean isDead;

    public Snake(){
        length = INITIAL_LENGTH;
        score = 0;

        x = new int[World.ALL_MODULES];
        y = new int[World.ALL_MODULES];

        isDead = false;
        isUp = isLeft = isDown = false;
        isRight = true;
    }

    public void update(){

    }

    public void render(Graphics2D g){
        g.setColor(Color.BLUE);
        g.drawRect(10, 10, 10, 10);
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }
}
