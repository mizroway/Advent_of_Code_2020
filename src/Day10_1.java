import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day10_1 {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("input_10.txt");
        Scanner scanner = new Scanner(filePath);
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }

        Collections.sort(list);

        List<Integer> differenceList = new ArrayList<>();
        
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1) > list.get(i) + 3) {
                break;
            }
            differenceList.add(list.get(i+1) - list.get(i));
        }
        //The charging outlet has an effective rating of 0 jolts
        //so add also diff between 0 and 1st element
        differenceList.add(list.get(0));

        int oneJoltDifference = Collections.frequency(differenceList, 1);
        int threeJoltDifference =  Collections.frequency(differenceList, 3) + 1;

        System.out.println("threeJoltDifference = " + threeJoltDifference);
        System.out.println("oneJoltDifference = " + oneJoltDifference);
        System.out.println("answer = " + (threeJoltDifference * oneJoltDifference));
    }
}
