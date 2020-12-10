import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7_2 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> input = new ArrayList<>(Files.readAllLines(Paths.get("input_7.txt")));
        List<Bag> bags = new ArrayList<>();
        List<Bag> tempBags = new ArrayList<>();
        List<Bag> newBags = new ArrayList<>();

        String lookingBag = "shiny gold bags contain";
        int quantity = 1;
        int totalBags = 0;
        tempBags = findBags(input, lookingBag, quantity);
        while (true) {
            tempBags.addAll(newBags);
            newBags.clear();
            bags.addAll(tempBags);
            for (Bag bag : tempBags) {
                newBags.addAll(findBags(input, bag.getAdjective() + " " + bag.getColor() + " bags contain", bag.getQuantity()));
            }
            if (tempBags.isEmpty()) {
                for (Bag bag : bags) {
                    System.out.println("bag = " + bag.getAdjective() + " " + bag.getColor() + " " + bag.getQuantity());
                    totalBags += bag.getQuantity();
                }
                System.out.println("totalBags = " + totalBags);
                break;
            }
            tempBags.clear();
        }
    }

    private static List<Bag> findBags(ArrayList<String> input, String lookingBag, int quantity) {
        List<Bag> bags = new ArrayList<>();
        Pattern shinyGold = Pattern.compile("(?<=" + lookingBag + " ).+");
        for (String s : input) {
            Matcher matcher = shinyGold.matcher(s);
            if (matcher.find()) {
                System.out.println("matcher = " + matcher.group(0));
                if (!matcher.group(0).equals("no other bags.")) {
                    String[] innerBags = matcher.group(0).split(", ");
                    for (String innerBag : innerBags) {
                        System.out.println("innerBag = " + innerBag);
                        String[] innerBagsParts = innerBag.split(" ");
                        bags.add(new Bag(Integer.parseInt(innerBagsParts[0]) * quantity, innerBagsParts[1], innerBagsParts[2]));
                    }
                }
            }
        }
        return bags;
    }

    static class Bag {
        private final int quantity;

        private final String adjective;
        private final String color;

        public Bag(int quantity, String adjective, String color) {
            this.quantity = quantity;
            this.adjective = adjective;
            this.color = color;
        }

        public int getQuantity() {
            return quantity;
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
