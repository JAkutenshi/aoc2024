import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Day1Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day1-input.txt"));

        PriorityQueue<Integer> firstList = new PriorityQueue<>();
        PriorityQueue<Integer> secondList = new PriorityQueue<>();

        while (input.hasNextLine()) {
            String[] numbers = input.nextLine().split("   ");

            firstList.add(Integer.parseInt(numbers[0]));
            secondList.add(Integer.parseInt(numbers[1]));
        }

        assert firstList.size() == secondList.size();

        int totalDistance = 0;

        while (!firstList.isEmpty()) {
            totalDistance += Math.abs(firstList.poll() - secondList.poll());
        }

        System.out.println(totalDistance);
    }
}