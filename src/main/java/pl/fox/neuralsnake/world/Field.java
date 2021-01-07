package pl.fox.neuralsnake.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class Field {

    private static final Logger LOG = LoggerFactory.getLogger(Field.class);

    private static final int RAND_POS_X = World.B_WIDTH / 10;
    private static final int RAND_POS_Y = World.B_HEIGHT / 10;
    private final java.util.List<Apple> apples;

    public Field(){
        apples = new java.util.ArrayList<>();
        generateApples();
    }


    public void generateApples(){
        int rand, x, y;

        rand = (int) (Math.random() * RAND_POS_X);
        x = (rand * World.MODULE_SIZE);

        rand = (int) (Math.random() * RAND_POS_Y);
        y = (rand * World.MODULE_SIZE);

        apples.add(new Apple(x, y, World.APPLE_CALORIES));

        if(apples.size() < World.FOOD_COUNT){
            generateApples();
        }
    }

    public void update(){
        apples.forEach(Apple::update);
    }

    public void render(Graphics2D g){
        apples.forEach(a -> a.render(g));
    }

    public java.util.List<Apple> getApples() {
        return apples;
    }
}
