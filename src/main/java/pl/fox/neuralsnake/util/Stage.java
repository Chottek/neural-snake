package pl.fox.neuralsnake.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Stage {

    public static final double SIG_MULTIPLIER = 0.1D;

    private final Stage previousStage;
    private final double[] output;
    private final byte[][] coefficients;

    public Stage(Stage previousStage, int size) {
        this.previousStage = previousStage;
        output = new double[size];

        coefficients = (previousStage != null) ? new byte[size][previousStage.output.length + 1] : new byte[0][0];
    }

    public void calculateOutput() {
        if (previousStage == null) {
            return;
        }

        for (int i = 0; i < coefficients.length; i++) {
            double sum = 0;

            for (int j = 0; j < coefficients[0].length - 1; j++) {
                sum += coefficients[i][j] * previousStage.output[j];
            }

            sum += coefficients[i][coefficients[0].length - 1] * SIG_MULTIPLIER;
            output[i] = sigmoid(sum);
        }
    }

    private double sigmoid(double value){
        return SIG_MULTIPLIER / ( 1 + Math.exp(-value/2D));
    }

    @Override
    public String toString() {
        return Arrays.stream(coefficients)
                .map(b -> Arrays.toString(b) + "\n")
                .collect(Collectors.joining("", "[", "]\n"));
    }

    public byte[][] getCoefficients() {
        return coefficients;
    }

    public double[] getOutput() {
        return output;
    }
}
