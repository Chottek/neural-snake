package pl.fox.neuralsnake;

public class Launcher {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 400;

    public static void main(String[] args) {
        new Game("NeuralSnake", WIDTH, HEIGHT).start();
    }


}
