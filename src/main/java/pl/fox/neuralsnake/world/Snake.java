package pl.fox.neuralsnake.world;


import pl.fox.neuralsnake.util.DNA;
import pl.fox.neuralsnake.util.NeuralNetwork;

import java.awt.*;

public class Snake {

    private static final int INITIAL_LENGTH = 3;

    private NeuralNetwork brain;
    private DNA dna;

    private int[] x;
    private int[] y;

    private int length;
    private int score;
    private int fitness;
    private int age;

    private boolean isUp, isDown, isLeft, isRight;
    private boolean isDead;
    private boolean isBrainSymetric;

    public Snake(DNA dna){
        brain = new NeuralNetwork();
        int dnaLength = brain.calculateCoefficientsNumber(isBrainSymetric) + 1;

        this.dna = (dna != null) ? dna : new DNA(true, dnaLength);
    }


    public void init(){
        score = 0;
        age = 0;
        length = INITIAL_LENGTH;

        x = new int[World.ALL_MODULES];
        y = new int[World.ALL_MODULES];

        isDead = false;
        isUp = isLeft = isDown = false;
        isRight = true;

        //TODO: Randomize spawn and initial direction later
        brain = new NeuralNetwork();
    }

    public void update(){

    }

    public void render(Graphics2D g){
        g.setColor(Color.RED); //Head color
        g.fillRect(x[0], y[0], World.MODULE_SIZE, World.MODULE_SIZE);
        g.setColor(dna.getColor()); //body color
        for(int i = 1; i < length; i++){
            g.drawRect(x[i], y[i], World.MODULE_SIZE, World.MODULE_SIZE);
        }
    }

    private void randomizeSpawn(){

    }

    public DNA getDna() {
        return dna;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public int getFitness() {
        return fitness;
    }
}
