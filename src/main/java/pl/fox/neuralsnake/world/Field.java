package pl.fox.neuralsnake.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Random;

public class Field {

    private static final Logger LOG = LoggerFactory.getLogger(Field.class);

    private static final int RAND_POS_X = World.B_WIDTH / 10;
    private static final int RAND_POS_Y = World.B_HEIGHT / 10;

    private final Random random;

    public Field(){
        random = new Random();

        generateFood();
    }

    private void generateFood(){

    }

    public void update(){

    }

    public void render(Graphics2D g){

    }


}
