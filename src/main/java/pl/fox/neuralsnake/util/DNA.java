package pl.fox.neuralsnake.util;

import java.util.stream.IntStream;

public class DNA {

    private final java.util.Random random;

    private java.awt.Color color;
    private byte[] helix;


    public DNA(boolean rand, int size) {
        random = new java.util.Random();
        randomizeColor();
        randomizeDNA(rand, size);
    }

    private void randomizeDNA(boolean random, int size) {
        helix = new byte[size];
        for (int i = 0; i < size; i++) {
            helix[i] = random ? (byte) Math.floor(Math.random() * 256d) : 0;
        }
    }

    private void randomizeColor(){
        color = new java.awt.Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
    }

    public void mutateNoise(double probability, double mag) {
        IntStream.range(0, helix.length).filter(i -> Math.random() < probability)
                .forEach(i -> helix[i] += (byte) (random.nextGaussian() * mag * 256));
    }

    public byte[] getHelix() {
        return helix;
    }

    public java.awt.Color getColor() {
        return color;
    }

    public void setColor(java.awt.Color color){
        this.color = color;
    }
}
