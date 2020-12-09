import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2_2 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> input = new ArrayList<>(Files.readAllLines(Paths.get("input_2.txt")));

        Pattern firstNumber = Pattern.compile("^[0-9]+");
        Pattern secondNumber = Pattern.compile("(?<=-)[0-9]+");
        Pattern lookingChar = Pattern.compile("(?<= ).");
        Pattern lookingWord = Pattern.compile("(?<= .: ).+");

        int min = 0, max = 0;
        char charToBeChecked = ' ';
        String word = " ";
        int correctPassCounter = 0;

        for (String s : input) {
            Matcher matcher = firstNumber.matcher(s);
            Matcher matcher2 = secondNumber.matcher(s);
            Matcher matcher3 = lookingChar.matcher(s);
            Matcher matcher4 = lookingWord.matcher(s);

            if (matcher.find()) {
                min = Integer.parseInt(matcher.group(0));
            }
            if (matcher2.find()) {
                max = Integer.parseInt((matcher2.group(0)));
            }
            if (matcher3.find()) {
                charToBeChecked = matcher3.group(0).charAt(0);
            }
            if (matcher4.find()) {
                word = matcher4.group(0);
            }

            if (checkPassword2(min, max, charToBeChecked, word)) {
                correctPassCounter++;
            }

        }
        System.out.println("correctPassCounter = " + correctPassCounter);
    }

    private static boolean checkPassword(int min, int max, char charToBeChecked, String word) {
        int counter = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == charToBeChecked) {
                counter++;
            }
        }
        return counter >= min && counter <= max;

    }

    private static boolean checkPassword2(int min, int max, char charToBeChecked, String word) {
        int counter = 0;
        if (word.charAt(min - 1) == charToBeChecked) {
            counter++;
        }
        if (word.charAt(max - 1) == charToBeChecked) {
            counter++;
        }
        return counter == 1;

    }
}
