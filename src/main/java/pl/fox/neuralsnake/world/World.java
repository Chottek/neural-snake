package pl.fox.neuralsnake.world;

import java.awt.*;

public class World {

    private final Snake snake;
    private final Field field;

    public World(){
        snake = new Snake();
        field = new Field();
    }

    public void update(){
        snake.update();
        field.update();
    }

    public void render(Graphics2D g){
        snake.render(g);
        field.render(g);
    }


}
