package pl.fox.neuralsnake.world;

import java.awt.*;

public class Apple {

    private final int x;
    private final int y;

    public Apple(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void update(){
        //TODO: Implement checking if any snake got this particular food
    }

    public void render(Graphics2D g){
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, World.MODULE_SIZE, World.MODULE_SIZE);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
