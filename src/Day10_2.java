import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day10_2 {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("input_10.txt");
        Scanner scanner = new Scanner(filePath);
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }

        Collections.sort(list);

        int [] tab = new int[list.size()];
        int temp = 0;
        for (Integer integer : list) {
            tab[temp++] = integer;
        }

        long[] sums = new long[tab[list.size() - 1] + 1];
        sums[0] = 1;
        for (int i = 0; i < list.size(); i++) {
            long x = 0, y = 0, z = 0;

            if (tab[i] >= 3) {
                x = sums[tab[i] - 3];
            }
            if (tab[i] >= 2) {
                y = sums[tab[i] - 2];
            }
            if (tab[i] >= 1) {
                z = sums[tab[i] - 1];
            }
            sums[tab[i]] = x + y + z;
        }

        System.out.println(sums[sums.length - 1]);
    }
}
