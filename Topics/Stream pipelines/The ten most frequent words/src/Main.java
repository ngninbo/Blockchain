import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int thenMostFrequentWords = 10;

        Arrays.stream(scanner.nextLine().split("[\\W]+"))
                .collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(thenMostFrequentWords)
                .forEach(el -> System.out.println(el.getKey()));
    }
}