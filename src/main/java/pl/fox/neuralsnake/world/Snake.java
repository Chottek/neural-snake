package pl.fox.neuralsnake.world;

import pl.fox.neuralsnake.util.DNA;
import pl.fox.neuralsnake.util.NeuralNetwork;

import java.awt.*;
import java.util.Random;
import java.util.stream.IntStream;

import static pl.fox.neuralsnake.world.World.*;

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

    private double deathHue = 180;

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
        isUp = isLeft = isDown = isRight = false;
        randomizeSpawn();
    }

    public void update(){
        if (isDead) {
            deathHue -= 0.6D;
            return;
        }

      //  manageHealth();
        move();
        checkCollision();
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
        Random rand = new Random();

        int iks = rand.nextInt(B_WIDTH);
        int ygr = rand.nextInt(B_HEIGHT);

        for (int i = 0; i < length; i++) {
            x[i] = iks - (i * 10);
            y[i] = ygr;
        }

        switch(rand.nextInt(4)){
            case 0: isUp = true; break;
            case 1: isDown = true; break;
            case 2: isLeft = true; break;
            case 3: isRight = true; break;
        }
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


    private void checkCollision() {
        if (IntStream.iterate(length, i -> i > 0, i -> i - 1).anyMatch(i -> (i > 3) && (x[0] == x[i]) && (y[0] == y[i]))) {
            isDead = true;
        }

        if (y[0] < 0 || y[0] >= World.B_HEIGHT - MODULE_SIZE) isDead = true;
        if (x[0] < 0 || x[0] >= World.B_WIDTH - MODULE_SIZE)  isDead = true;
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

        age += 0.01D;
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

    public boolean isDead() {
        return isDead;
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

    public double getDeathHue() {
        return deathHue;
    }

    private int getEnviromentalInfo(){
        return 0;
    }




}
