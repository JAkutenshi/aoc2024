import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5Part1 {
    // Rules set: key=page, values -- set of pages that must be after the key page. Note: page's number is less 100
    public static final Map<Integer, Set<Integer>> rules = new HashMap<>(100);

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day5-input.txt"));

        int answer = 0;

        boolean rulesAreRead = false;

        while (input.hasNextLine()) {
            String line = input.nextLine();

            if (line.isEmpty()) {
                rulesAreRead = true;
            } else {
                if (!rulesAreRead) {
                    handleRule(line);
                } else {
                    answer += handleUpdate(line);
                }
            }
        }

        System.out.println(answer);
    }

    private static void handleRule(String line) {
        String[] numbers = line.split("\\|");

        int pageKey = Integer.parseInt(numbers[0]);
        int pageAfter = Integer.parseInt(numbers[1]);

        if (rules.containsKey(pageKey)) {
            rules.get(pageKey).add(pageAfter);
        } else {
            Set<Integer> pagesAfter = new HashSet<>();
            pagesAfter.add(pageAfter);
            rules.put(pageKey, pagesAfter);
        }
    }

    private static int handleUpdate(String line) {
        int[] pages = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();

        Set<Integer> pagesBefore = new HashSet<>();

        for (int page : pages) {
            Set<Integer> pagesAfter = rules.computeIfAbsent(page, k -> new HashSet<>());
            if (intersects(pagesBefore, pagesAfter)) {
                return 0;
            }

            pagesBefore.add(page);
        }

        return pages[pages.length / 2];
    }

    private static boolean intersects(Set<Integer> first, Set<Integer> second) {
        for (Integer i : first) {
            if (second.contains(i)) {
                return true;
            }
        }

        return false;
    }
}
