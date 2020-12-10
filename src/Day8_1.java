import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day8_1 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> input = new ArrayList<>(Files.readAllLines(Paths.get("input_8.txt")));
        int accumulator = 0;

        String [] operations = new String[input.size()];
        int [] values = new int[input.size()];
        boolean [] visited = new boolean[input.size()];

        for (int i = 0; i < input.size(); i++) {
            String [] line = input.get(i).split(" ");
            operations[i] = line[0];
            values[i] = Integer.parseInt(line[1]);
            visited[i] = false;
            System.out.println("operation = " + operations[i]);
            System.out.println("value = " + values[i]);
        }

        int index = 0;
        while (true) {
            if (visited[index]) {
                System.out.println("accumulator = " + accumulator);
                break;
            }
            switch (operations[index]) {
                case "acc":
                    accumulator += values[index];
                    visited[index] = true;
                    index++;
                    break;
                case "jmp":
                    visited[index] = true;
                    index += values[index];
                    break;
                case "nop":
                    visited[index] = true;
                    index++;
                    break;
            }

        }

    }
}
