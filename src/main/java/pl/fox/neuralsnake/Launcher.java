package pl.fox.neuralsnake;

public class Launcher {

    public static final String TITLE = "NeuralSnake";
    public static final int WIDTH = 700;
    public static final int HEIGHT = 400;

    public static void main(String[] args) {
        new Game(TITLE, WIDTH, HEIGHT).start();
    }


}
