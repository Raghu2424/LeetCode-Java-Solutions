import java.util.*;

class Solution {
    static class Interval {
        int l, r, weight, id;

        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    static class DPVal {
        long weight;
        List<Integer> indices;

        DPVal(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> cur = intervals.get(i);
            arr[i] = new Interval(cur.get(0), cur.get(1), cur.get(2), i);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l) return Integer.compare(a.l, b.l);
            if (a.r != b.r) return Integer.compare(a.r, b.r);
            if (a.weight != b.weight) return Integer.compare(b.weight, a.weight);
            return Integer.compare(a.id, b.id);
        });

        int[] starts = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = arr[i].l;
        }

        DPVal[][] dp = new DPVal[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new DPVal(0, new ArrayList<>());
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int nextIdx = binarySearch(starts, arr[i].r + 1);

            for (int k = 1; k <= 4; k++) {
                DPVal skip = dp[i + 1][k];

                long takeWeight = arr[i].weight + dp[nextIdx][k - 1].weight;
                List<Integer> takeIndices = new ArrayList<>();
                takeIndices.add(arr[i].id);
                takeIndices.addAll(dp[nextIdx][k - 1].indices);
                Collections.sort(takeIndices);

                DPVal take = new DPVal(takeWeight, takeIndices);
                dp[i][k] = isBetter(take, skip) ? take : skip;
            }
        }

        DPVal ans = new DPVal(0, new ArrayList<>());

        for (int k = 1; k <= 4; k++) {
            if (isBetter(dp[0][k], ans)) {
                ans = dp[0][k];
            }
        }

        int[] result = new int[ans.indices.size()];

        for (int i = 0; i < ans.indices.size(); i++) {
            result[i] = ans.indices.get(i);
        }

        return result;
    }

    private boolean isBetter(DPVal a, DPVal b) {
        if (a.weight != b.weight) {
            return a.weight > b.weight;
        }

        int len = Math.min(a.indices.size(), b.indices.size());

        for (int i = 0; i < len; i++) {
            if (!a.indices.get(i).equals(b.indices.get(i))) {
                return a.indices.get(i) < b.indices.get(i);
            }
        }

        return a.indices.size() < b.indices.size();
    }

    private int binarySearch(int[] starts, int target) {
        int low = 0, high = starts.length;

        while (low < high) {
            int mid = (low + high) >>> 1;

            if (starts[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}
