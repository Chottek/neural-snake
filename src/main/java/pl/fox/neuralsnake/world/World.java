package pl.fox.neuralsnake.world;

import java.awt.*;

public class World {

    protected static final int B_WIDTH = pl.fox.neuralsnake.Launcher.WIDTH;
    protected static final int B_HEIGHT = pl.fox.neuralsnake.Launcher.HEIGHT;
    protected static final int ALL_MODULES = B_WIDTH * B_HEIGHT;
    protected static final int MODULE_SIZE = 10;

    protected static final int GENERATION_COUNT = 5;
    protected static final int FOOD_COUNT = 4;

    private Nest nest;
    private Field field;

    private boolean isPaused;

    public World(){
        init();
    }

    public void init(){
        nest = new Nest(GENERATION_COUNT);
        field = new Field();
        isPaused = false;
    }

    public void update(){
        if(isPaused){
            return;
        }

        nest.update();
        field.update();
    }

    public void render(Graphics2D g){
        nest.render(g);
        field.render(g);
    }


    //Getters & Setters

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isPaused() {
        return isPaused;
    }
}
