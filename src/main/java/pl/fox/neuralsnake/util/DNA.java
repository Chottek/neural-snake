package pl.fox.neuralsnake.util;


public class DNA {

    private final java.util.Random random;
    private byte[] helix;

    public DNA(boolean rand, int size) {
        random = new java.util.Random();
        randomizeDNA(rand, size);
    }

    private void randomizeDNA(boolean random, int size) {
        helix = new byte[size];
        for (int i = 0; i < size; i++) {
            helix[i] = random ? (byte) Math.floor(Math.random() * 256d) : 0;
        }
    }

    public byte[] getHelix() {
        return helix;
    }
}
