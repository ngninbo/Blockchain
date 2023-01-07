import java.util.Arrays;
import java.util.Scanner;

class ArrayCalc {

    // static nested class
    public static class MinMaxPair {
        private final int min;
        private final int max;

        public MinMaxPair(int first, int second) {
            this.min = first;
            this.max = second;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }
    }

    // find min and max elements
    public static MinMaxPair findMinMax(int[] array) {

        int min = Arrays.stream(array).min().orElse(Integer.MIN_VALUE);
        int max = Arrays.stream(array).max().orElse(Integer.MAX_VALUE);

        return new MinMaxPair(min, max);
    }
}

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // input array 
        final int length = 10;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }

        ArrayCalc.MinMaxPair p = ArrayCalc.findMinMax(array);

        System.out.println("min = " + p.getMin());
        System.out.println("max = " + p.getMax());
    }
}