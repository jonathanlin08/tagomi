package com.lin.tagomi.assessment.partI;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CoreJavaAssessment {
    public static void printHash(int lines) {
        for (int rowIndex = 1; rowIndex <= lines; rowIndex ++) {
            for (int columnIndex = 1; columnIndex <= lines; columnIndex++)
                if (columnIndex <= lines - rowIndex) {
                    System.out.print(" ");
                } else {
                    System.out.print("#");
                }
            System.out.println();
        }
    }

    public static void countFrequency(List<Integer> list) {
        ConcurrentHashMap<Integer, String> map2 = new ConcurrentHashMap<>(list.size());
        for (Integer k : list) {
            map2.merge(k, "one", (oldVal, value) -> {
                switch (oldVal) {
                    case "one":
                        return "couple";
                    case "couple":
                        return "few";
                    case "few":
                    case "many":
                        return "many";
                    default:
                        return "one";
                }
            });
        }
        final String result = map2.reduce(1,
                (k, v) -> k + ": " + v,
                (r1, r2) -> r1 + "\n" + r2
        );
        System.out.println(result);
    }

    public static void main(String[] args) {
        CoreJavaAssessment.printHash(4);
        System.out.println("--------------");
        List<Integer> input = Arrays.asList(3, 5, -1, 5, 6, 2, 0, -5, 3, 5, 3, 0, 3);
        countFrequency(input);
    }
}
