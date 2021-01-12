package pl.fox.neuralsnake.util;

public class Layer {

    private final java.util.List<Neuron> neurons;

    public Layer(int inputsNum, int neuronsNum){
        neurons = new java.util.ArrayList<>();

        initNeurons(inputsNum, neuronsNum);
    }

    private void initNeurons(int inputsNum, int neuronsNum){
        for(int i = 0; i < neuronsNum; i++){
            neurons.add(new Neuron(inputsNum));
        }
    }

    public int getSize(){
        return neurons.size();
    }

    public java.util.List<Neuron> getNeurons(){
        return neurons;
    }


}
