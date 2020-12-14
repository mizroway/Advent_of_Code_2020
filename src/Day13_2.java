import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day13_2 {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("input_13.txt");
        Scanner scanner = new Scanner(filePath);

        long earliestTimestamp = 0;

        String secondLine = scanner.nextLine();
        String[] lineElements = secondLine.split(",");
        System.out.println("secondLine = " + secondLine);

        int[] indexes = new int[9];
        int[] busID = new int[9];
        int index = 0;
        for (int i = 0; i < lineElements.length; i++) {
            if (!lineElements[i].equals("x")) {
                busID[index] = Integer.parseInt(lineElements[i]);
                indexes[index] = i;
                index++;
            }
        }

        earliestTimestamp = busID[0];
        long increment = busID[0];

        for (int j = 1; j < busID.length; j++) {

            while ((earliestTimestamp + indexes[j]) % busID[j] != 0) {
                earliestTimestamp += increment;
            }
            increment *= busID[j];
        }
        System.out.println("earliestTimestamp = " + earliestTimestamp);
    }
}





