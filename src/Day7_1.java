import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7_1 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> input = new ArrayList<>(Files.readAllLines(Paths.get("input_7.txt")));
        Set<Bag> bags = new HashSet<>();
        Set<Bag> tempBags = new HashSet<>();
        Set<Bag> newBags = new HashSet<>();

        String lookingBag = "shiny gold";
        tempBags = findBags(input, lookingBag);
        while (true) {
            tempBags.removeAll(bags);
            newBags.addAll(tempBags);
            bags.addAll(tempBags);
            for (Bag bag : tempBags) {
                newBags.addAll(findBags(input, bag.getAdjective() + " " + bag.getColor()));
            }
            if (newBags.isEmpty()) {
                for (Bag bag : bags) {
                    System.out.println("bag = " + bag.getAdjective() + " " + bag.getColor());
                }
                System.out.println("bags = " + bags.size());
                break;
            }
            tempBags.clear();
            tempBags.addAll(newBags);
            newBags.clear();
        }
    }

    private static Set<Bag> findBags(ArrayList<String> input, String lookingBag) {
//        String lookingBag = "shiny gold";
        Set<Bag> bags = new HashSet<>();
        Pattern shinyGold = Pattern.compile("(?<=\\d )" + lookingBag);
        for (String s : input) {
            Matcher matcher = shinyGold.matcher(s);
            if (matcher.find()) {
//                System.out.println("s = " + s);
                String[] words = s.split(" ");
                bags.add(new Bag(words[0], words[1]));
            }
        }
        return bags;
    }

    static class Bag {
        private final String adjective;
        private final String color;

        public Bag(String adjective, String color) {
            this.adjective = adjective;
            this.color = color;
        }

        public String getAdjective() {
            return adjective;
        }

        public String getColor() {
            return color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bag bag = (Bag) o;
            return Objects.equals(adjective, bag.adjective) &&
                    Objects.equals(color, bag.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(adjective, color);
        }
    }

}
