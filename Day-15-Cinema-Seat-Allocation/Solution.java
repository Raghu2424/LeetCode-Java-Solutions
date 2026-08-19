import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> reservedMap = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col >= 2 && col <= 9) {
                reservedMap.put(
                    row,
                    reservedMap.getOrDefault(row, 0)
                        | (1 << (col - 2))
                );
            }
        }

        int totalGroups = (n - reservedMap.size()) * 2;

        int leftMask = 15;    // Seats 2-5
        int rightMask = 240;  // Seats 6-9
        int middleMask = 60;  // Seats 4-7

        for (int mask : reservedMap.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;

            if (leftFree && rightFree) {
                totalGroups += 2;
            } else if (leftFree || rightFree || (mask & middleMask) == 0) {
                totalGroups += 1;
            }
        }

        return totalGroups;
    }
}
