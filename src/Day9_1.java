import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9_1 {

    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("input_9.txt");
        Scanner scanner = new Scanner(filePath);
        List<Long> list = new ArrayList<>();
        while (scanner.hasNextLong()) {
            list.add(scanner.nextLong());
        }
        for (Long number : list){
            System.out.println("number = " + number);
        }

        boolean pairFound = false;

        for (int i = 25; i < list.size(); i++) {
            pairFound = false;
            for (int j = i - 25; j < i; j++) {
                if (pairFound) {
                    break;
                }
                for (int k = j; k < i + 1; k++) {
                    if (list.get(i) == (list.get(j) + list.get(k + 1))) {
                        pairFound = true;
                        break;
                    }
                }
            }
            if (!pairFound) {
                System.out.println("i = " + i);
                System.out.println("val = " + list.get(i));
                break;
            }
        }
    }
}
