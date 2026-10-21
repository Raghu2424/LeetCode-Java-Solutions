import java.util.Arrays;

class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        char[] sorted = arr.clone();
        Arrays.sort(sorted);

        if (Arrays.equals(arr, sorted)) return 0;
        if (n == 2) return -1;

        char min = sorted[0], max = sorted[n - 1];

        if (arr[0] == min || arr[n - 1] == max) return 1;

        if (arr[0] == max && arr[n - 1] == min) {
            boolean uniqueMax = true, uniqueMin = true;

            for (int i = 1; i < n; i++)
                if (arr[i] == max) uniqueMax = false;

            for (int i = 0; i < n - 1; i++)
                if (arr[i] == min) uniqueMin = false;

            if (uniqueMax && uniqueMin) return 3;
        }

        return 2;
    }
}
