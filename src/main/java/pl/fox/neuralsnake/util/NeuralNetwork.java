package pl.fox.neuralsnake.util;

import java.util.stream.IntStream;

public class NeuralNetwork {

    private Stage[] stages;

    public NeuralNetwork(int[] stageSizes) {
        stages = new Stage[stageSizes.length];
        Stage previous = null;
        for (int i = 0; i < stageSizes.length; i++) {
            stages[i] = new Stage(previous, stageSizes[i]);
            previous = stages[i];
        }
    }

    public double[] calculateOutput(double[] input){
        IntStream.range(0, input.length).forEach(i -> stages[0].getOutput()[i] = input[i]);
        IntStream.range(1, stages.length).forEach(i -> stages[i].calculateOutput());
        return stages[stages.length - 1].getOutput();
    }

    public int calculateCoefficientsNumber(boolean isSymmetrical, int[] stageSizes){
        if (stageSizes.length < 2){
            return 0;
        }
        int sum = 0;
        for (int i = 1; i < stageSizes.length; i++) {
            sum += (isSymmetrical) ? (stageSizes[i] * (stageSizes[i - 1] + 1) + 1) / 2 : stageSizes[i] * (stageSizes[i - 1] + 1);
        }
        return sum;
    }

    public void loadCoefficients(byte[] coefficients) {
        int idx = 0;

        for (int s = 1; s < stages.length; s++) {
            for (int i = 0; i < stages[s].getCoefficients().length; i++) {
                for (int j = 0; j < stages[s].getCoefficients()[0].length; j++) {
                    stages[s].getCoefficients()[i][j] = coefficients[idx++];
                }
            }
        }
    }

    public void loadSymmetrically(byte[] coefficients) {
        int idx = 0;
        for (int s = 1; s < stages.length; s++) {
            if (stages[s].getCoefficients().length % 2 == 1) {
                return;
            }
            for (int i = 0; i < (stages[s].getCoefficients().length) / 2; i++) {
                for (int j = 0; j < stages[s].getCoefficients()[0].length; j++) {
                    stages[s].getCoefficients()[i][j] = coefficients[idx];
                    stages[s].getCoefficients()[stages[s].getCoefficients().length - 1 - i][stages[s].getCoefficients()[0].length - 1 - j] = coefficients[idx++];
                }
            }
        }
    }

    //TODO: Implement update and render methods of drawing neural network

}
