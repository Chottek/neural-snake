package pl.fox.neuralsnake.world;


import pl.fox.neuralsnake.util.DNA;
import pl.fox.neuralsnake.util.NeuralNetwork;

import java.awt.*;

import static pl.fox.neuralsnake.world.World.MODULE_SIZE;

public class Snake {

    private static final int INITIAL_LENGTH = 3;
    private static final int MAX_VIEW_DISTANCE = 400;

    private NeuralNetwork brain;
    private DNA dna;

    private int[] x;
    private int[] y;

    private int length;
    private int score;
    private int health;
    private int age;

    private boolean isUp, isDown, isLeft, isRight;
    private boolean isDead;
    private boolean isBrainSymetric;

    public Snake(DNA dna){
        brain = new NeuralNetwork();
        int dnaLength = brain.calculateCoefficientsNumber(isBrainSymetric) + 1;
        this.dna = (dna != null) ? dna : new DNA(true, dnaLength);
        reloadCoeffs();
    }

    public void init(){
        score = 0;
        age = 0;
        length = INITIAL_LENGTH;

        health = World.APPLE_CALORIES * 3 / 2;

        x = new int[World.ALL_MODULES];
        y = new int[World.ALL_MODULES];

        isDead = false;
        isUp = isLeft = isDown = false;
        isRight = true;

        //TODO: Randomize spawn and initial direction later
    }

    public void update(){
        manageHealth();
        move();
    }

    public void render(Graphics2D g){
        g.setColor(Color.RED); //Head color
        g.fillRect(x[0], y[0], MODULE_SIZE, MODULE_SIZE);
        g.setColor(dna.getColor()); //body color
        for(int i = 1; i < length; i++){
            g.drawRect(x[i], y[i], MODULE_SIZE, MODULE_SIZE);
        }
    }

    private void randomizeSpawn(){

    }

    private void move(){
        for (int i = length; i > 0; i--) {
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];
        }

        if(isLeft)
            x[0] -= MODULE_SIZE;

        if(isRight)
            x[0] += MODULE_SIZE;

        if(isUp)
            y[0] -= MODULE_SIZE;

        if(isDown)
            y[0] += MODULE_SIZE;
    }

    private boolean getEnviromentalInfo(){
        //Locate food, walls and modules
        return true;
    }

    private void manageHealth(){
        if(health > 3 * World.APPLE_CALORIES){
            health = 3 * World.APPLE_CALORIES;
        }

        health -= World.HEALTH_SPOIL;

        if(health <= 0){
            isDead = true;
            score /= 2;
        }

        age += 0.1D;
    }

    private void reloadCoeffs(){
        if(isBrainSymetric){
            brain.loadSymmetrically(dna.getHelix());
        }else{
            brain.loadCoefficients(dna.getHelix());
        }
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
        return score + (health / 4);
    }
}
