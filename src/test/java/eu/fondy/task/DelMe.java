package eu.fondy.task;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class DelMe {


    @Test
    public void go() {
        Map<DenominationType, Map<Integer, Integer>> res = calc(50122);
        System.out.println("poundsResult: " + res.get(DenominationType.POUND));
        System.out.println("penceResult: " + res.get(DenominationType.PENCE));

    }


    private Map<DenominationType, Map<Integer, Integer>> calc(int initialValue) {
        // could be 1 array, but I think it's easy to maintain code with 2 in the future
        int[] denominationPounds = {50, 20, 10, 5, 2};
        int[] denominationPence = {50, 20, 10, 5, 2, 1};

        int remaining = initialValue;
        Map<Integer, Integer> poundsResult = new HashMap<>();
        Map<Integer, Integer> penceResult = new HashMap<>();

        for (int i : denominationPounds) {
            int value = remaining / (i * 100);
            poundsResult.put(i, value);
            remaining -= value * i * 100;
        }

        for (int i : denominationPence) {
            int value = remaining / i;
            penceResult.put(i, value);
            remaining -= value * i;
        }

        if (remaining > 0) {
            System.out.println("Left: " + remaining);
            throw new RuntimeException("Left more than 0");
        }

        return Map.of(DenominationType.POUND, poundsResult,
                DenominationType.PENCE, penceResult);
    }




    private enum DenominationType {
        POUND,
        PENCE

    }

}
