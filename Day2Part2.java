import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Day2Part2 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day2-input.txt"));

        int totalSafeReports = 0;

        while (input.hasNextLine()) {
            String[] numbers = input.nextLine().split(" ");

            int[] levels = Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();

            if (isSafeReport(levels)) {
                totalSafeReports++;
            } else {
                // It really don't like the way, but it is straightforward and possible for levels < 10 and 1000 reports
                for (int i = 0; i < levels.length; i++) {
                    if (isSafeReport(truncate(levels, i))) {
                        totalSafeReports++;
                        break;
                    }
                }
            }
        }

        System.out.println(totalSafeReports);
    }

    private static int[] truncate(int[] levels, int excludeIdx) {
        int[] truncatedReport = new int[levels.length - 1];

        int truncatedIdx = 0;
        for (int i = 0; i < levels.length; i++) {
            if (i != excludeIdx) {
                truncatedReport[truncatedIdx++] = levels[i];
            }
        }
        return truncatedReport;
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

            if (!isSafeStep(diff)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isSafeStep(int diff) {
        return 1 <= diff && diff <= 3;
    }
}
