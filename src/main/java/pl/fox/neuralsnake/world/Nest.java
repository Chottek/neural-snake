package pl.fox.neuralsnake.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.fox.neuralsnake.Handler;
import pl.fox.neuralsnake.util.DNA;
import pl.fox.neuralsnake.util.GeneticUtils;

import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

public class Nest {

    private static final Logger LOG = LoggerFactory.getLogger(Nest.class);

    private final java.util.List<Snake> snakes;
    private final int size;

    private double mutationRate = 0.02D;
    private double currentFittestValue = 0;
    private int generationCount;

    private int tempDeathCount = 0;

    private DNA bestDNA;
    private double record;

    private final Handler handler;

    public Nest(int size, Handler handler){
        this.handler = handler;
        snakes = new java.util.ArrayList<>();
        this.size = size;
        record = 0;

        genFirstGeneration();
    }

    public void update(){
        tempDeathCount = 0;

        snakes.forEach(s -> {
            s.update();
            checKIfIsDead(s);
            processFitnessValues(s);
        });

       // makeNewSnakes();
        removeDeadSnakes();
    }

    public void render(Graphics2D g){
        snakes.forEach(s -> s.render(g));
    }

    private void genFirstGeneration(){
        snakes.clear();
        IntStream.range(0, size).forEach(i -> snakes.add(new Snake(null, handler)));
        snakes.forEach(Snake::init);
        generationCount = 1;
        LOG.info("Initialized a Nest of {} snakes", size);
    }

    private void makeNewSnakes(){
        if(tempDeathCount > 0){
            IntStream.range(0, tempDeathCount).forEach(i -> {
                addNewSnake();
                generationCount += 1 / (double) size;
            });
        }
    }

    private void removeDeadSnakes(){
        snakes.removeIf(s -> s.getDeathHue() <= 0);
    }

    private void checKIfIsDead(Snake s){
        if(s.isDead()){
            tempDeathCount++;
        }
    }

    private void processFitnessValues(Snake s){
        if (s.getFitness() > currentFittestValue) {
            currentFittestValue = s.getFitness();
        }
        if (s.getFitness() > record) {
            record = s.getFitness();
            bestDNA = s.getDna();
        }
    }

    private void addNewSnake(){
        mutationRate = 10 / currentFittestValue;
        java.util.List<Snake> pool = createMatingPool();

        int id1 = (int) (Math.random() * pool.size());
        int id2 = (int) (Math.random() * pool.size());

        DNA firstParent = pool.get(id1).getDna();
        DNA secondParent = pool.get(id2).getDna();

        snakes.add(new Snake(GeneticUtils.byteWiseCrossover(firstParent, secondParent, mutationRate), handler));
    }


    private java.util.List<Snake> createMatingPool(){
        java.util.List<Snake> pool = new java.util.ArrayList<>();

        double greatestFitness = 0;

        for(Snake s : snakes) {
            greatestFitness = (s.getFitness() > greatestFitness) ? s.getFitness() : greatestFitness;
        }

        for(Snake s : snakes){
            int amount = (int) (s.getFitness() * 100 / greatestFitness);
            IntStream.range(0, amount).mapToObj(i -> s).forEach(pool::add);
        }
        return pool;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }
}
