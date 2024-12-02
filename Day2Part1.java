import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Day2Part1 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day2-input.txt"));

        int totalSafeReports = 0;

        while (input.hasNextLine()) {
            String[] numbers = input.nextLine().split(" ");

            int[] levels = Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();

            totalSafeReports += isSafeReport(levels) ? 1 : 0;
        }

        System.out.println(totalSafeReports);
    }

    public static boolean isSafeReport(int[] levels) {
        // Assumes that levels count > 1
        boolean isAscending = levels[0] < levels[1];

        // We don't want to check isAscending everytime, so let's introduce the diff's lambda before
        BiFunction<Integer, Integer, Integer> calculateDiff = isAscending
                ? (currentLevel, nextLevel) -> nextLevel - currentLevel
                : (currentLevel, nextLevel) -> currentLevel - nextLevel;

        for (int levelIdx = 0; levelIdx < levels.length - 1; levelIdx++) {
            int diff = calculateDiff.apply(levels[levelIdx], levels[levelIdx + 1]);
            boolean isSafeStep = 1 <= diff && diff <= 3;

            if (!isSafeStep) {
                return false;
            }
        }

        return true;
    }
}
