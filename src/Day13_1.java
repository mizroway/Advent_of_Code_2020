import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day13_1 {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("input_13.txt");
        Scanner scanner = new Scanner(filePath);
        List<Integer> list = new ArrayList<>();

        int departureTime = Integer.parseInt(scanner.nextLine());

        String secondLine = scanner.nextLine();
        String[] lineElements = secondLine.split(",");

        Map<Integer, Integer> mapa = new HashMap<>();
        for (String lineElement : lineElements) {
            if (!lineElement.equals("x")) {
                int temp = Integer.parseInt(lineElement);
                while (temp < departureTime) {
                    temp += Integer.parseInt(lineElement);
                }
                mapa.put(Integer.parseInt(lineElement), temp);
            }
        }
        int firstBus = Collections.min(mapa.values());
        for (Map.Entry<Integer, Integer> integerIntegerEntry : mapa.entrySet()) {
            if (integerIntegerEntry.getValue().equals(firstBus)) {
                System.out.println(integerIntegerEntry.getKey() * (integerIntegerEntry.getValue() - departureTime));
                break;
            }
        }

    }
}
