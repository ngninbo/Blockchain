import java.util.Scanner;
import java.util.function.Function;

class CurryConcat {

    public static String calc(String first, String second, String third) {

        Function<String, Function<String, Function<String, String>>> stringFun = 
                s1 -> s2 -> s3 -> s1.toLowerCase().concat(s3.toUpperCase()).concat(s2.toLowerCase());

        return stringFun.apply(first).apply(second).apply(third);
    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.next(), scanner.next(), scanner.next()));
    }
}