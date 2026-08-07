import java.util.*;

class Solution {

    // factors[d] = {count of 2, 3, 5, 7} in digit d
    private static final int[][] factors = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String num, long t) {

        // Count prime factors of t: 2, 3, 5, 7
        int[] target = new int[4];

        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                target[i]++;
                t /= primes[i];
            }
        }

        // Any remaining factor cannot be produced by digits 1-9.
        if (t != 1) {
            return "-1";
        }

        int[] targetDigits = getDigitCounts(target);

        // If the required digits are already longer than num,
        // construct the smallest possible answer.
        if (countDigits(targetDigits) > num.length()) {
            return build(targetDigits);
        }

        // Count prime factors in the whole number.
        int[] prefix = new int[4];

        for (int i = 0; i < num.length(); i++) {
            int digit = num.charAt(i) - '0';

            for (int j = 0; j < 4; j++) {
                prefix[j] += factors[digit][j];
            }
        }

        int firstZero = num.indexOf('0');

        // num itself works.
        if (firstZero == -1 && contains(prefix, target)) {
            return num;
        }

        /*
         * Change one digit from right to left.
         * Everything before that digit stays the same.
         */
        for (int i = num.length() - 1; i >= 0; i--) {

            int currentDigit = num.charAt(i) - '0';

            // Remove current digit from the prefix.
            for (int j = 0; j < 4; j++) {
                prefix[j] -= factors[currentDigit][j];
            }

            // We cannot keep a prefix that is after the first zero.
            if (firstZero != -1 && i > firstZero) {
                continue;
            }

            int remainingSpaces = num.length() - 1 - i;

            // Try the next larger digit.
            for (int biggerDigit = currentDigit + 1;
                 biggerDigit <= 9;
                 biggerDigit++) {

                int[] needed = new int[4];

                for (int j = 0; j < 4; j++) {
                    needed[j] = Math.max(
                        0,
                        target[j]
                            - prefix[j]
                            - factors[biggerDigit][j]
                    );
                }

                int[] neededDigits = getDigitCounts(needed);

                int digitsNeeded = countDigits(neededDigits);

                if (digitsNeeded <= remainingSpaces) {

                    StringBuilder answer = new StringBuilder();

                    // Original prefix.
                    answer.append(num.substring(0, i));

                    // Bigger digit.
                    answer.append(biggerDigit);

                    // Extra positions can contain 1.
                    for (int k = digitsNeeded;
                         k < remainingSpaces;
                         k++) {
                        answer.append('1');
                    }

                    // Required digits.
                    answer.append(build(neededDigits));

                    return answer.toString();
                }
            }
        }

        // No answer with the same length.
        int digitsNeeded = countDigits(targetDigits);

        StringBuilder answer = new StringBuilder();

        for (int i = digitsNeeded;
             i < num.length() + 1;
             i++) {
            answer.append('1');
        }

        answer.append(build(targetDigits));

        return answer.toString();
    }

    /*
     * Convert required prime factors into the minimum number
     * of digits.
     */
    private int[] getDigitCounts(int[] count) {

        int c2 = count[0];
        int c3 = count[1];
        int c5 = count[2];
        int c7 = count[3];

        int[] result = new int[10];

        // 2^3 = 8
        result[8] = c2 / 3;
        c2 %= 3;

        // 3^2 = 9
        result[9] = c3 / 2;
        c3 %= 2;

        // 2^2 = 4
        result[4] = c2 / 2;
        c2 %= 2;

        /*
         * Combine one 2 and one 3 into 6.
         */
        if (c2 == 1 && c3 == 1) {
            result[6] = 1;
            c2 = 0;
            c3 = 0;
        }

        /*
         * Important special case:
         *
         * 3 × 4 = 12
         *
         * is better represented as
         *
         * 2 × 6 = 12
         *
         * for the required construction.
         */
        if (c3 == 1 && result[4] > 0) {
            result[4]--;
            result[2]++;
            result[6]++;
            c3 = 0;
        }

        result[2] += c2;
        result[3] += c3;
        result[5] = c5;
        result[7] = c7;

        return result;
    }

    private int countDigits(int[] digitCounts) {

        int total = 0;

        for (int i = 2; i <= 9; i++) {
            total += digitCounts[i];
        }

        return total;
    }

    private String build(int[] digitCounts) {

        StringBuilder result = new StringBuilder();

        // Ascending order gives the smallest suffix.
        for (int digit = 2; digit <= 9; digit++) {

            for (int i = 0; i < digitCounts[digit]; i++) {
                result.append(digit);
            }
        }

        return result.toString();
    }

    private boolean contains(int[] have, int[] need) {

        for (int i = 0; i < 4; i++) {
            if (have[i] < need[i]) {
                return false;
            }
        }

        return true;
    }
}
