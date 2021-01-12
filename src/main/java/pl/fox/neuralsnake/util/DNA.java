package pl.fox.neuralsnake.util;

import java.awt.*;
import java.util.stream.IntStream;

public class DNA {

    private final java.util.Random random;
    private byte[] helix;
    private final Color color;

    public DNA(boolean rand, int size, Color color) {
        random = new java.util.Random();
        this.color = color;
        randomizeDNA(rand, size);
    }

    private void randomizeDNA(boolean random, int size) {
        helix = new byte[size];
        IntStream.range(0, size).forEach(i -> helix[i] = random ? (byte) Math.floor(Math.random() * 256d) : 0);
    }

    public void mutateNoise(double probability, double mag) {
        IntStream.range(0, helix.length).filter(i -> Math.random() < probability)
                .forEach(i -> helix[i] += (byte) (random.nextGaussian() * mag * 256));
    }

    public byte[] getHelix() {
        return helix;
    }

    public Color getColor() {
        return color;
    }
}
