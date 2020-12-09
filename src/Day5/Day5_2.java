package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day5_2 {

    static Map<Integer, Set<Integer>> seats = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ArrayList<String> input = new ArrayList<>(Files.readAllLines(Paths.get("input_5.txt")));
        int biggestSeatID = 0;

        for (String s : input) {
            Seat seat = findSeat(s);
            if (seats.containsKey(seat.getX())) {
                HashSet temp = new HashSet();
                temp = (HashSet) seats.get(seat.getX());
                temp.add(seat.getY());
                seats.replace(seat.getX(), temp);
            } else {
                seats.put(seat.getX(), new HashSet<>(Arrays.asList(seat.getY())));
            }


//            int seatID = findSeat(s);
//            if (seatID > biggestSeatID) {
//                biggestSeatID = seatID;
//            }
//            System.out.println("biggestSeatID = " + biggestSeatID);
        }
        for (Map.Entry<Integer, Set<Integer>> entry : seats.entrySet()) {
            if (entry.getValue().size() != 8 ) {

                System.out.println(entry);
            }
        }
    }

    private static Seat findSeat(String s) {
        String rowInput = s.substring(0, 7);

        String columnInput = s.substring(7, 10);

        int row = 0;
        int column = 0;
        int seatID = 0;

        for (int i = 0; i < rowInput.length(); i++) {
            if (rowInput.charAt(i) == 'B') {
                row += Math.pow(2, rowInput.length() - 1 - i);
            }
        }

        for (int i = 0; i < columnInput.length(); i++) {
            if (columnInput.charAt(i) == 'R') {
                column += Math.pow(2, columnInput.length() - 1 - i);
            }
        }
        return new Seat(row, column);


//        return seatID = row * 8 + column;
    }
}

class Seat {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Seat(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
