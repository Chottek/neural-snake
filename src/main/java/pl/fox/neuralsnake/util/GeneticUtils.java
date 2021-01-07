package pl.fox.neuralsnake.util;

import java.util.stream.IntStream;

public class GeneticUtils {

    public static DNA crossover(DNA first, DNA second, double probability) {
        DNA newDNA = new DNA(false, first.getHelix().length);
        int swapsNum = first.getHelix().length / 8;
        int[] swaps = new int[swapsNum + 1];
        IntStream.range(0, swaps.length - 1).forEach(i -> swaps[i] = (int) Math.floor(Math.random() * 8 * first.getHelix().length));
        swaps[swapsNum] = first.getHelix().length * 8;
        java.util.Arrays.sort(swaps);

        int swapID = 0;
        boolean isThis = true;
        for (int i = 0; i < first.getHelix().length * 8; i++) {
            if (i >= swaps[swapID]) {
                isThis = !isThis;
                swapID++;
            }
            int bit;
            if (isThis) {
                bit = ((first.getHelix()[i / 8] >> (i % 8) & 1));
            } else {
                bit = ((second.getHelix()[i / 8] >> (i % 8) & 1));
            }
            if(Math.random() < probability){
                bit = 1 - bit;
            }
            newDNA.getHelix()[i / 8] |= (bit << (i%8));
        }
        return newDNA;
    }

    public static DNA byteWiseCrossover(DNA first, DNA second, double probability){
        DNA newDNA = new DNA(false, first.getHelix().length);
        int swapsNum = first.getHelix().length / 8;
        int[] swaps = new int[swapsNum + 1];
        IntStream.range(0, swaps.length - 1).forEach(i -> swaps[i] = (int) Math.floor(Math.random() * first.getHelix().length) * 8);
        swaps[swapsNum] = first.getHelix().length * 8;
        java.util.Arrays.sort(swaps);
        boolean isThat = true;
        int swapID = 0;
        for (int i = 0; i < 8 * first.getHelix().length; i++){
            if (i >= swaps[swapID]){
                swapID++;
                isThat = !isThat;
            }
            int bit = 0;
            if (isThat){
                bit = ((first.getHelix()[i/8] >> (i%8)) & 1);
            }
            else {
                bit = ((second.getHelix()[i/8] >> (i%8)) & 1);
            }
            if (Math.random() < probability) bit = 1-bit;
            newDNA.getHelix()[i/8] |= (bit << (i%8));
        }
        return newDNA;
    }



}
