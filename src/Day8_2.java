import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day8_2 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> input = new ArrayList<>(Files.readAllLines(Paths.get("input_8.txt")));

        String [] operations = new String[input.size()];
        int [] values = new int[input.size()];
        boolean [] visited = new boolean[input.size()];

        boolean solutionFound = false;

        int accumulator = 0;

        for (int i = 0; i< input.size(); i++) {
            int index = 0;
            int temp = 0;
            for (int j = 0; j < input.size(); j++) {
                String [] line = input.get(j).split(" ");
                operations[j] = line[0];
                values[j] = Integer.parseInt(line[1]);
                visited[j] = false;
            }
            if (operations[i].equals("jmp")) {
                operations[i] = "nop";
            } else {
                continue;
            }
            if (solutionFound) {
                System.out.println("accumulator = " + accumulator);
                System.out.println("HURRAY");
                break;
            }
            accumulator = 0;

            while (true) {
                if (temp == visited.length - 1) {
                    solutionFound = true;
                    System.out.println("accumulator = " + accumulator);
                    break;
                }
                if (visited[index] || index < 0 || index > visited.length - 1) {
                    System.out.println("accumulator = " + accumulator);
                    break;
                }
                System.out.println("operation = " + operations[index]);
                System.out.println("value = " + values[index]);
                System.out.println("index = " + index);
                switch (operations[index]) {
                    case "acc":
                        accumulator += values[index];
                        visited[index] = true;
                        temp = index;
                        index++;
                        break;
                    case "jmp":
                        visited[index] = true;
                        temp = index;
                        index += values[index];
                        break;
                    case "nop":
                        visited[index] = true;
                        temp = index;
                        index++;
                        break;
                }

            }
        }

    }
}
