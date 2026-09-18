import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] left = new int[26];
        int[] right = new int[26];

        Arrays.fill(left, n);
        Arrays.fill(right, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            left[c] = Math.min(left[c], i);
            right[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Find all valid minimal intervals
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            // Only start from the first occurrence
            if (i != left[c]) {
                continue;
            }

            int end = right[c];
            boolean valid = true;

            for (int j = i; j <= end; j++) {
                int x = s.charAt(j) - 'a';

                // Character occurs before this interval
                if (left[x] < i) {
                    valid = false;
                    break;
                }

                end = Math.max(end, right[x]);
            }

            if (valid) {
                intervals.add(new int[]{i, end});
            }
        }

        // Choose intervals with earliest ending positions
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();
        int lastEnd = -1;

        for (int[] interval : intervals) {
            if (interval[0] > lastEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                lastEnd = interval[1];
            }
        }

        return result;
    }
}
