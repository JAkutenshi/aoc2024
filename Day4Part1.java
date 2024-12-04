import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4Part1 {
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

        // Step 2: test chars around 'X' and add matches count to the answer
        int answer = 0;

        for (int lineIdx = 0; lineIdx < LINES_COUNT; lineIdx++) {
            for (int charIdx = 0; charIdx < LINE_WIDTH; charIdx++) {
                if (inputMatrix[lineIdx][charIdx] == 'X') {
                    answer += countMatchesAround(inputMatrix, lineIdx, charIdx);
                }
            }
        }

        System.out.println(answer);
    }

    // X M A S
    private static int countMatchesAround(char[][] inputMatrix, int lineIdx, int charIdx) {
        int matchesCount = 0;

        matchesCount += testN(inputMatrix, lineIdx, charIdx) ? 1 : 0;
        matchesCount += testNE(inputMatrix, lineIdx, charIdx) ? 1 : 0;
        matchesCount += testE(inputMatrix, lineIdx, charIdx) ? 1 : 0;
        matchesCount += testSE(inputMatrix, lineIdx, charIdx) ? 1 : 0;
        matchesCount += testS(inputMatrix, lineIdx, charIdx) ? 1 : 0;
        matchesCount += testSW(inputMatrix, lineIdx, charIdx) ? 1 : 0;
        matchesCount += testW(inputMatrix, lineIdx, charIdx) ? 1 : 0;
        matchesCount += testNW(inputMatrix, lineIdx, charIdx) ? 1 : 0;

        return matchesCount;
    }

    private static boolean testN(char[][] inputMatrix, int lineIdx, int charIdx) {
        if (lineIdx - 3 < 0) {
            return false;
        }

        return inputMatrix[lineIdx - 1][charIdx] == 'M'
                && inputMatrix[lineIdx - 2][charIdx] == 'A'
                && inputMatrix[lineIdx - 3][charIdx] == 'S';
    }

    private static boolean testNE(char[][] inputMatrix, int lineIdx, int charIdx) {
        if (lineIdx - 3 < 0 || charIdx + 3 >= LINE_WIDTH) {
            return false;
        }

        return inputMatrix[lineIdx - 1][charIdx + 1] == 'M'
                && inputMatrix[lineIdx - 2][charIdx + 2] == 'A'
                && inputMatrix[lineIdx - 3][charIdx + 3] == 'S';
    }

    private static boolean testE(char[][] inputMatrix, int lineIdx, int charIdx) {
        if (charIdx + 3 >= LINE_WIDTH) {
            return false;
        }

        return inputMatrix[lineIdx][charIdx + 1] == 'M'
                && inputMatrix[lineIdx][charIdx + 2] == 'A'
                && inputMatrix[lineIdx][charIdx + 3] == 'S';
    }

    private static boolean testSE(char[][] inputMatrix, int lineIdx, int charIdx) {
        if (lineIdx + 3 >= LINES_COUNT || charIdx + 3 >= LINE_WIDTH) {
            return false;
        }

        return inputMatrix[lineIdx + 1][charIdx + 1] == 'M'
                && inputMatrix[lineIdx + 2][charIdx + 2] == 'A'
                && inputMatrix[lineIdx + 3][charIdx + 3] == 'S';
    }

    private static boolean testS(char[][] inputMatrix, int lineIdx, int charIdx) {
        if (lineIdx + 3 >= LINES_COUNT) {
            return false;
        }

        return inputMatrix[lineIdx + 1][charIdx] == 'M'
                && inputMatrix[lineIdx + 2][charIdx] == 'A'
                && inputMatrix[lineIdx + 3][charIdx] == 'S';
    }

    private static boolean testSW(char[][] inputMatrix, int lineIdx, int charIdx) {
        if (lineIdx + 3 >= LINES_COUNT || charIdx - 3 < 0) {
            return false;
        }

        return inputMatrix[lineIdx + 1][charIdx - 1] == 'M'
                && inputMatrix[lineIdx + 2][charIdx - 2] == 'A'
                && inputMatrix[lineIdx + 3][charIdx - 3] == 'S';
    }


    private static boolean testW(char[][] inputMatrix, int lineIdx, int charIdx) {
        if (charIdx - 3 < 0) {
            return false;
        }

        return inputMatrix[lineIdx][charIdx - 1] == 'M'
                && inputMatrix[lineIdx][charIdx - 2] == 'A'
                && inputMatrix[lineIdx][charIdx - 3] == 'S';
    }

    private static boolean testNW(char[][] inputMatrix, int lineIdx, int charIdx) {
        if (lineIdx - 3 < 0 || charIdx - 3 < 0) {
            return false;
        }

        return inputMatrix[lineIdx - 1][charIdx - 1] == 'M'
                && inputMatrix[lineIdx - 2][charIdx - 2] == 'A'
                && inputMatrix[lineIdx - 3][charIdx - 3] == 'S';
    }
}
