import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Day6_2 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> input = new ArrayList<>(Files.readAllLines(Paths.get("input_6.txt")));
        Set<Character> answers = new HashSet<>();
        Set<Character> tempAnswers = new HashSet<>();
        int counter = 0;
        int totalSize = 0;
        for (int i = 0; i < input.size(); i++) {

            char[] tab = input.get(i).toCharArray();
            for (char c : tab) {
                tempAnswers.add(c);
            }
            counter++;
            if (counter > 1 && tempAnswers.size() > 0) {
                answers.retainAll(tempAnswers);
            } else if (counter == 1 && tempAnswers.size() > 0){
                answers.addAll(tempAnswers);
            }

            if (input.get(i).isEmpty() || i == input.size() - 1) {
                System.out.println("answers = " + answers);
                totalSize += answers.size();
                System.out.println("totalSize = " + totalSize);
                answers = new HashSet<>();
                counter = 0;
            }

            tempAnswers.clear();
        }
    }
}
