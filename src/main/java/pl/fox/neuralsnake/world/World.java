package pl.fox.neuralsnake.world;

import java.awt.*;

public class World {

    public static final int B_WIDTH = pl.fox.neuralsnake.Launcher.WIDTH;
    public static final int B_HEIGHT = pl.fox.neuralsnake.Launcher.HEIGHT;
    public static final int ALL_MODULES = B_WIDTH * B_HEIGHT;

    //private final java.util.List<Snake> snakes;

    private final Snake snake;
    private final Field field;


    public World(){
        //snakes = new java.util.ArrayList<>();

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
