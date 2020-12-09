import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4_2 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> input = new ArrayList<>(Files.readAllLines(Paths.get("input_4.txt")));
        Map<Integer, Map<String, String>> mapa = new HashMap<>();
        Pattern code = Pattern.compile("[a-z]+");
        Pattern value = Pattern.compile("(?<=:).+");
        Map<String, String> interMapa = new HashMap<>();

        int counter = 0;
        int correctDocuments = 0;

        for (int i = 0; i < input.size(); i++) {

            String[] tab = input.get(i).split(" ");
            for (String s1 : tab) {
                Matcher matcher = code.matcher(s1);
                Matcher matcher2 = value.matcher(s1);
                if (matcher.find() && matcher2.find()) {
                    interMapa.put(matcher.group(0), matcher2.group(0));
                }
            }

            if (input.get(i).isEmpty() || i == input.size() - 1) {
                mapa.put(counter, interMapa);
                interMapa = new HashMap<>();
                counter++;
            }
        }
        for (Map.Entry<Integer, Map<String, String>> integerMapEntry : mapa.entrySet()) {
            if (integerMapEntry.getValue().size() == 8 ||
                    (integerMapEntry.getValue().size() == 7 && !integerMapEntry.getValue().containsKey("cid"))) {
                if (checkRequirements(integerMapEntry.getValue())) {
                    correctDocuments++;
                    System.out.println("integerMapEntry = " + integerMapEntry);
                }
            }
        }
        System.out.println("correctDocuments = " + correctDocuments);

    }

    private static boolean checkRequirements(Map<String, String> document) {

        return checkByr(document) && checkIyr(document) && checkEyr(document) && checkHgt(document)
                && checkHcl(document) && checkEcl(document) && checkPid(document);
    }

    private static boolean checkPid(Map<String, String> document) {
            String pid = document.get("pid");
            Pattern passport = Pattern.compile("^[0-9]{9}$");
            Matcher matcher = passport.matcher(pid);

            return matcher.find();
    }

    private static boolean checkEcl(Map<String, String> document) {
            String ecl = document.get("ecl");
            return  (ecl.equals("amb") || ecl.equals("blu") || ecl.equals("brn") || ecl.equals("gry") ||
                    ecl.equals("grn") || ecl.equals("hzl") ||  ecl.equals("oth"));


    }

    private static boolean checkHcl(Map<String, String> document) {
            String hcl = document.get("hcl");
            Pattern hairColor = Pattern.compile("#[0-9a-f]{6}");
            Matcher matcher = hairColor.matcher(hcl);

            return matcher.find();
    }

    private static boolean checkHgt(Map<String, String> document) {
            String hgt = document.get("hgt");
            Pattern height = Pattern.compile("[0-9]+");
            Matcher matcher = height.matcher(hgt);
            boolean result = false;

            if (hgt.endsWith("cm")) {
                if (matcher.find()) {
                    result = (Integer.parseInt(matcher.group(0)) >= 150 && Integer.parseInt(matcher.group(0)) <= 193);
                }
            } else if (hgt.endsWith("in")) {
                if (matcher.find()) {
                    result = (Integer.parseInt(matcher.group(0)) >= 59 && Integer.parseInt(matcher.group(0)) <= 76);
                }
            }
            return result;
    }

    private static boolean checkEyr(Map<String, String> document) {
            int eyr = Integer.parseInt(document.get("eyr"));
            return (eyr >= 2020 && eyr <= 2030);
    }

    private static boolean checkIyr(Map<String, String> document) {
            int iyr = Integer.parseInt(document.get("iyr"));
            return (iyr >= 2010 && iyr <= 2020);
    }

    private static boolean checkByr(Map<String, String> document) {
            int byr = Integer.parseInt(document.get("byr"));
            return (byr >= 1920 && byr <= 2002);
    }
}
