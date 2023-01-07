import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] array = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = Math.abs(j - i);
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(array[i])
                    .replaceAll("[\\[\\]]", "")
                    .replace(", ", " "));
        }
    }
}