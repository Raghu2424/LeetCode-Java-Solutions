import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            best[i] = Integer.MAX_VALUE;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        int sum = 0;
        int answer = Integer.MAX_VALUE;
        int minLength = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            sum += arr[i - 1];

            if (map.containsKey(sum - target)) {
                int start = map.get(sum - target);
                int length = i - start;

                // Combine with a previous non-overlapping subarray
                if (best[start] != Integer.MAX_VALUE) {
                    answer = Math.min(answer, length + best[start]);
                }

                minLength = Math.min(minLength, length);
            }

            best[i] = minLength;
            map.put(sum, i);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
