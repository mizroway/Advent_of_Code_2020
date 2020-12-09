import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day3_2 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> input = new ArrayList<>(Files.readAllLines(Paths.get("input_3.txt")));
        
        long totalCounterOfTrees = 1;
        int rightTab [] = {1, 3, 5, 7, 1};
        int downTab [] = {1, 1, 1, 1, 2};

        for (int i = 0; i< rightTab.length; i++) {
            totalCounterOfTrees *= (checkTrees(rightTab[i], downTab[i], input)) ;
        }

        System.out.println("totalCounterOfTrees = " + totalCounterOfTrees);
    }
    private static int checkTrees(int right, int down, ArrayList<String> input) {
        int column = 1;
        int counterOfTrees = 0;
        for (int i = 0; i < input.size(); i += down) {
            char [] tab = new char[input.get(1).length() + 1];

            for (int j = 1; j < tab.length ; j++) {
                tab[j] = input.get(i).charAt(j - 1);
            }

            if (tab[column] == '#') {
                counterOfTrees++;
            }
            column += right;

            if(column > tab.length - 1) {
                column = column % (tab.length - 1);
            }
        }
        return counterOfTrees;
    }
}
