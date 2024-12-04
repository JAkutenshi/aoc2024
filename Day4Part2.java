import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4Part2 {
    public static final int LINES_COUNT = 140;
    public static final int LINE_WIDTH = 140;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day4-input.txt"));

        // Step 1: read the input.
        // We know from the input file that it's a matrix 140x140 and total size of ANSI chars is <20KB, so we can read
        // it completely.

        char[][] inputMatrix = new char[LINES_COUNT][LINE_WIDTH];

        int readLineIdx = 0;

        while (input.hasNextLine()) {
            inputMatrix[readLineIdx++] = input.nextLine().toCharArray();
        }

        // Step 2: test chars around 'A' and add matches count to the answer
        int answer = 0;

        for (int lineIdx = 1; lineIdx < LINES_COUNT - 1; lineIdx++) {
            for (int charIdx = 1; charIdx < LINE_WIDTH - 1; charIdx++) {
                if (inputMatrix[lineIdx][charIdx] == 'A') {
                    answer += countMatchesAround(inputMatrix, lineIdx, charIdx);
                }
            }
        }

        System.out.println(answer);
    }

    private static int countMatchesAround(char[][] inputMatrix, int lineIdx, int charIdx) {
        int matchesCount = 0;

        matchesCount += test(inputMatrix, lineIdx, charIdx)
                ? 1
                : 0;

        return matchesCount;
    }

    private static boolean test(char[][] inputMatrix, int lineIdx, int charIdx) {
        char topLeft = inputMatrix[lineIdx - 1][charIdx - 1];
        char topRight = inputMatrix[lineIdx - 1][charIdx + 1];
        char bottomLeft = inputMatrix[lineIdx + 1][charIdx - 1];
        char bottomRight = inputMatrix[lineIdx + 1][charIdx + 1];

        return (topLeft == 'M' && topRight == 'M' && bottomLeft == 'S' && bottomRight == 'S')
                || (topLeft == 'S' && topRight == 'S' && bottomLeft == 'M' && bottomRight == 'M')
                || (topLeft == 'S' && bottomLeft == 'S' && topRight == 'M' && bottomRight == 'M')
                || (topLeft == 'M' && bottomLeft == 'M' && topRight == 'S' && bottomRight == 'S');
    }
}
