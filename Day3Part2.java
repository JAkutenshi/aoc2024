import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)" +
                "|(do\\(\\))" +
                "|(don't\\(\\))");

        Scanner input = new Scanner(new File("day3-input.txt"));

        boolean ignore = false;

        int answer = 0;

        while (input.hasNextLine()) {
            String line = input.nextLine();
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                String op = matcher.group(0);

                switch (op) {
                    case "do()":
                        ignore = false;
                        break;
                    case "don't()":
                        ignore = true;
                    default: {
                        if (ignore) {
                            break;
                        }

                        String num1 = matcher.group(1);
                        String num2 = matcher.group(2);

                        int mulResult = Integer.parseInt(num1) * Integer.parseInt(num2);
                        System.out.println(op + " = " + mulResult);

                        answer += mulResult;
                    }
                }


            }
        }

        System.out.println(answer);
    }
}
