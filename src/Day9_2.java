import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day9_2 {

    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("input_9.txt");
        Scanner scanner = new Scanner(filePath);
        List<Long> list = new ArrayList<>();
        while (scanner.hasNextLong()) {
            list.add(scanner.nextLong());
        }
        for (Long number : list) {
            System.out.println("number = " + number);
        }

        boolean pairFound;

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
                long searchingVal = list.get(i);
                for (int j = 0; j < i; j++) {
                    long temp = 0;
                    for (int k = j; k < i; k++) {
                        if (temp < searchingVal) {
                            temp += list.get(k);
                        } else if (temp > searchingVal) {
                            break;
                        } else {
                            System.out.println("temp = " + temp);
                            System.out.println("j = " + j);
                            System.out.println("k = " + k);
                            List<Long> contigousList = new ArrayList<>();
                            for (int l = j; l < k + 1; l++) {
                                contigousList.add(list.get(l));
                            }
                            int minNumberIndex = list.indexOf(Collections.min(contigousList));
                            int maxNumberIndex = list.indexOf(Collections.max(contigousList));

                            System.out.println("answer: " + (list.get(minNumberIndex) + list.get(maxNumberIndex)));
                            break;
                        }
                    }
                }
                break;
            }
        }
    }
}
