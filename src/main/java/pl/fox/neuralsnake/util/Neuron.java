package pl.fox.neuralsnake.util;

import java.util.stream.IntStream;

import static pl.fox.neuralsnake.util.GeneticUtils.sigmoid;

public class Neuron {

    public static final double SIG_MULTIPLIER = 0.1D;
    private final java.util.List<Double> weights;


    public Neuron(int inputsNum) {
        weights = new java.util.ArrayList<>();
        randomlyInit(inputsNum);
    }

    private void randomlyInit(int inputsNum){
        for(int i = 0; i < inputsNum + 1; i++){
            weights.add(Math.random() * 2 - 1);
        }
    }

    public void addNewWeights(java.util.List<Double> genome){
        final int size = weights.size();
        weights.clear();
        for(int i = 0; i < size; i++){
            weights.add(genome.get(0));
            genome.remove(0);
        }
    }

    public double getOutput(java.util.List<Double> inputs){
        double sum = IntStream.range(0, weights.size() - 1).mapToDouble(i -> weights.get(i) * inputs.get(i)).sum();
        sum += weights.get(weights.size() - 1) * -1;
        return sigmoid(sum);
    }

    public java.util.List<Double> getWeights() {
        return weights;
    }
}
