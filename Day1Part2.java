import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Day1Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day1-input.txt"));

        List<Long> firstListNumbers = new ArrayList<>(1000);
        Map<Long, Integer> secondListNumbersHistogram = new HashMap<>(1000);

        while (input.hasNextLine()) {
            String[] numbers = input.nextLine().split("   ");

            firstListNumbers.add(Long.parseLong(numbers[0]));

            secondListNumbersHistogram.compute(
                    Long.parseLong(numbers[1]),
                    (n, count) -> count == null
                            ? 1
                            : count + 1
            );
        }

        // I thought that long might be not enough, but there wasn't any overflow, so BigInteger waits for a proper task
        long totalDistance = 0;

        for (long number : firstListNumbers) {
            if (!secondListNumbersHistogram.containsKey(number)) {
                continue;
            }

           totalDistance += number * secondListNumbersHistogram.get(number);
        }

        System.out.println(totalDistance);
    }
}