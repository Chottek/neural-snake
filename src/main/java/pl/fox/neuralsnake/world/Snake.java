package pl.fox.neuralsnake.world;

import pl.fox.neuralsnake.Handler;
import pl.fox.neuralsnake.util.DNA;
import pl.fox.neuralsnake.util.NeuralNetwork;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static pl.fox.neuralsnake.world.World.*;

public class Snake {

    private static final int INITIAL_LENGTH = 3;
    private static final int MAX_VIEW_DISTANCE = 400;

    private final NeuralNetwork brain;
    private DNA dna;

    private int[] x;
    private int[] y;

    private int length;
    private int score;
    private double health;
    private double age;
    private int direction; // 0 - left, 1 - right, 2 - up, 3 - down


    private boolean isDead;
    private boolean isBrainSymetric;

    private double deathHue = 180;

    private final Handler handler;

    public Snake(DNA dna, Handler handler){
        this.handler = handler;
        brain = new NeuralNetwork();
        int dnaLength = brain.calculateCoefficientsNumber(isBrainSymetric) + 1;
        this.dna = (dna != null) ? dna : new DNA(true, dnaLength, Color.GREEN);
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
        randomizeSpawn();
    }

    public void update(){
        if (isDead) {
            deathHue -= 0.6D;
            return;
        }

        manageHealth();
        move();
        handleAppleGather();
      //  checkCollision();
        think();
    }

    public void render(Graphics2D g){
        g.setColor(Color.RED); //Head color
        g.fillRect(x[0], y[0], MODULE_SIZE, MODULE_SIZE);
        g.setColor(processedColor()); //body color
        for(int i = 1; i < length; i++){
            g.drawRect(x[i], y[i], MODULE_SIZE, MODULE_SIZE);  //add deathHue
        }
    }

    private Color processedColor(){
        Color c = dna.getColor();
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) deathHue);
    }

    private void randomizeSpawn(){
        Random rand = new Random();

        int iks = MODULE_SIZE * rand.nextInt((B_WIDTH / MODULE_SIZE) + 1);
        int ygr = MODULE_SIZE * rand.nextInt((B_HEIGHT / MODULE_SIZE) + 1);

        for (int i = 0; i < length; i++) {
            x[i] = iks - (i * 10);
            y[i] = ygr;
        }

        direction = rand.nextInt(4);
    }

    private void move(){
        for (int i = length; i > 0; i--) {
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];
        }

        switch(direction){
            case 0: x[0] -= MODULE_SIZE; break;
            case 1: x[0] += MODULE_SIZE; break;
            case 2: y[0] -= MODULE_SIZE; break;
            case 3: y[0] += MODULE_SIZE; break;
        }
    }


    private void checkCollision() {
        if (IntStream.iterate(length, i -> i > 0, i -> i - 1).anyMatch(i -> (i > 4) && (x[0] == x[i]) && (y[0] == y[i]))) {
            isDead = true;
        }

        if (y[0] < 0 || y[0] >= World.B_HEIGHT - MODULE_SIZE) isDead = true;
        if (x[0] < 0 || x[0] >= World.B_WIDTH - MODULE_SIZE)  isDead = true;
    }

    private void think(){
        double[] inputs = getInputs();

        double[] output = brain.calculateOutput(inputs);

       // System.out.println(Arrays.toString(output));

        double out = (DoubleStream.of(output).sum());

       // System.out.println(out);

        direction = (int) out;

        if((direction == 0 && out == 1) || (direction == 1 && out == 0) ||
                (direction == 2 && out == 3) || (direction == 3 && out == 2)){
            think();
        }
     //  System.err.println(direction);
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

    private void handleAppleGather(){
        java.util.List<Apple> apples = handler.getField().getApples();

        for(int i = 0; i < handler.getField().getApples().size(); i++){
            if(getHeadX() == apples.get(i).getX() && getHeadY() == apples.get(i).getY()){
                handler.getField().getApples().remove(i);
                addLength();
                addScore(APPLE_CALORIES);
                handler.getField().generateApples();
            }
        }
    }

    private void reloadCoeffs(){
        if(isBrainSymetric){
            brain.loadSymmetrically(dna.getHelix());
        }else{
            brain.loadCoefficients(dna.getHelix());
        }
    }

    private double[] getInputs(){
        double[] inputs = new double[4];

        inputs[0] = getHeadX();
        inputs[1] = getHeadY();

        Apple closest = null;
        double closestX = 999, closestY = 999;
        for(Apple a: handler.getField().getApples()){
            double ex = Math.abs(getHeadX() - a.getX());
            double ygr = Math.abs(getHeadY() - a.getY());
            if(ex < closestX && ygr < closestY){
                closestX = ex;
                closestY = ygr;
                closest = a;
            }
        }

        inputs[2] = closest.getX();
        inputs[3] = closest.getY();

        return inputs;
    }

    //Getters / setters

    public void addScore(int score){
        this.score += score;
    }

    public void addLength(){
        length++;
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

    public int getHeadX() {
        return x[0];
    }

    public int getHeadY(){
        return y[0];
    }

    public int getFitness() {
        return score + (int) (health / 4);
    }

    public double getDeathHue() {
        return deathHue;
    }

    private int getEnviromentalInfo(){
        return 0;
    }




}
