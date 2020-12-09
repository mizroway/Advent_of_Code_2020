import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1_2 {

    public static void main(String[] args) throws IOException {

        ArrayList<String> input = new ArrayList<>(Files.readAllLines(Paths.get("input_1.txt")));

        int [] tab = new int[input.size()];
        int index = 0;

        for (String s : input) {
            tab[index++] = Integer.parseInt(s);
        }

        for (int i = 0; i < tab.length - 1; i++) {
            for (int j = i + 1; j < tab.length - 1; j++) {
                for (int k = j + 1; k < tab.length - 1; k++) {

                    if (tab[i] + tab[j] + tab[k] == 2020) {
                        System.out.println(tab[i] + " " + tab[j] + " " + tab[k]);
                        System.out.println(tab[i] * tab[j] * tab[k]);
                        break;

                    }
                }
            }
        }


    }
}
