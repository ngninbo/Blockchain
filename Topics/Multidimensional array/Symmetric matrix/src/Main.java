import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dim = scanner.nextInt();
        int[][] array = new int[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                array[i][j] = scanner.nextInt();
            }
        }

        System.out.println(isSymmetric(array) ? "YES" : "NO");
    }

    private static boolean isSymmetric(int[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 1; j + i < matrix[0].length; j++) {
                if (matrix[i + j][i] != matrix[i][i + j]) {
                    return false;
                }
            }
        }
        return true;
    }
}