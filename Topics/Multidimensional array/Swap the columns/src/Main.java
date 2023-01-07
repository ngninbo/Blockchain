import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int[][] array = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = scanner.nextInt();
            }
        }

        int firstColumn = scanner.nextInt();
        int secondColumn = scanner.nextInt();

        swap(array, firstColumn, secondColumn);
        printMatrix(array);
    }

    private static void printMatrix(int[][] array) {
        for (int[] ints : array) {
            System.out.println(Arrays.toString(ints)
                    .replaceAll("[\\[\\]]", "")
                    .replace(", ", " "));
        }
    }

    public static void swap(int[][] array, int k, int l) {
        for (int i = 0; i < array.length; i++) {
            int temp = array[i][k];
            array[i][k] = array[i][l];
            array[i][l] = temp;
        }
    }
}