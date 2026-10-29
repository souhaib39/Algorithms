
package org.souhaib;

import java.util.*;

public class Main {

    static class Result {
        int size;
        String algorithm;
        double avgTime;

        Result(int size, String algorithm, double avgTime) {
            this.size = size;
            this.algorithm = algorithm;
            this.avgTime = avgTime;
        }
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 10000, 1_000_000};

        List<Result> results = new ArrayList<>();
        Random random = new Random();

        for (int size : sizes) {

            int[] arr = new int[size];

            for (int i = 0; i < size; i++) {
                arr[i] = random.nextInt(size * 10);
            }

            Arrays.sort(arr);

            for (int i = 0; i < 100; i++) {
                int target = arr[random.nextInt(size)];
                BinarySearch.binarySearch_iterative(arr, target);
            }

            int repetitions;

            if (size <= 1000) {
                repetitions = 10000;
            } else if (size <= 10000) {
                repetitions = 2000;
            } else {
                repetitions = 100;
            }

            measure(results, arr, size, repetitions,
                    "Linear Search (Iterative)", random, 1);

            if (size <= 10000) {
                measure(results, arr, size, repetitions,
                        "Linear Search (Recursive)", random, 2);
            }

            measure(results, arr, size, repetitions,
                    "Binary Search (Iterative)", random, 3);

            measure(results, arr, size, repetitions,
                    "Binary Search (Recursive)", random, 4);
        }

        System.out.println();

        System.out.printf("%-12s | %-30s | %-18s%n",
                "Size", "Algorithm", "Avg Time (s)");

        System.out.println(
                "--------------------------------------------------------------------------");

        for (Result r : results) {
            System.out.printf("%-12d | %-30s | %.9f%n",
                    r.size, r.algorithm, r.avgTime);
        }
    }

    static void measure(
            List<Result> results,
            int[] arr,
            int size,
            int repetitions,
            String algorithm,
            Random random,
            int type) {

        long totalTime = 0;

        for (int i = 0; i < repetitions; i++) {

            int target = arr[random.nextInt(size)];

            long start = System.nanoTime();

            switch (type) {
                case 1:
                    BinarySearch.linearSearch_iterative(arr, target);
                    break;

                case 2:
                    BinarySearch.linearSearch_recursion(arr, target);
                    break;

                case 3:
                    BinarySearch.binarySearch_iterative(arr, target);
                    break;

                case 4:
                    BinarySearch.binarySearch_recursion(arr, target);
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unknown algorithm type: " + type);
            }

            long end = System.nanoTime();
            totalTime += end - start;
        }

        double avgTime =
                totalTime / (double) repetitions / 1_000_000_000.0;

        results.add(new Result(size, algorithm, avgTime));
    }
}
