package pl.fox.neuralsnake.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.List;

public class Field {

    private static final Logger LOG = LoggerFactory.getLogger(Field.class);

    private static final int RAND_POS_X = World.B_WIDTH / 10;
    private static final int RAND_POS_Y = World.B_HEIGHT / 10;
    private final java.util.List<Apple> apples;

    public Field(){
        apples = new java.util.ArrayList<>();
        generateFood();
    }

    private void generateFood(){
        int rand, x, y;
        for(int i = 0; i < World.FOOD_COUNT; i++){
            rand = (int) (Math.random() * RAND_POS_X);
            x = (rand * World.MODULE_SIZE);

            rand = (int) (Math.random() * RAND_POS_Y);
            y = (rand * World.MODULE_SIZE);

            apples.add(new Apple(x, y, World.APPLE_CALORIES));
        }
        LOG.info("Initialized a Field of {} apples", apples.size());
    }

    public void update(){
        apples.forEach(Apple::update);
    }

    public void render(Graphics2D g){
        apples.forEach(a -> a.render(g));
    }

    public List<Apple> getApples() {
        return apples;
    }
}
