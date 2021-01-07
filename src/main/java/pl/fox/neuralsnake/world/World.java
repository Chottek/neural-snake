package pl.fox.neuralsnake.world;

import java.awt.*;

public class World {

    protected static final int B_WIDTH = pl.fox.neuralsnake.Launcher.WIDTH;
    protected static final int B_HEIGHT = pl.fox.neuralsnake.Launcher.HEIGHT;
    protected static final int ALL_MODULES = B_WIDTH * B_HEIGHT;
    protected static final int MODULE_SIZE = 10;

    //private final java.util.List<Snake> snakes;

    private Snake snake;
    private Field field;


    public World(){
        //snakes = new java.util.ArrayList<>();

        snake = new Snake();
        field = new Field();

        snake.init();
        field.init();
    }

//    public void init(){
//        snake = new Snake();
//        field = new Field();
//
//        snake.init();
//        field.init();
//    }

    public void update(){
        snake.update();
        field.update();
    }

    public void render(Graphics2D g){
        snake.render(g);
        field.render(g);
    }

}
