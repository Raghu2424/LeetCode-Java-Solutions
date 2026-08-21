class Solution {
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    private long countAmounts(long target, int[] coins) {
        int n = coins.length;
        long count = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            long currentLcm = 1;
            int setBits = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    setBits++;

                    currentLcm = lcm(currentLcm, coins[i]);

                    if (currentLcm > target) {
                        break;
                    }
                }
            }

            if (currentLcm <= target) {
                long values = target / currentLcm;

                if (setBits % 2 == 1) {
                    count += values;
                } else {
                    count -= values;
                }
            }
        }

        return count;
    }

    public long findKthSmallest(int[] coins, int k) {
        long minCoin = coins[0];

        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }

        long low = 1;
        long high = minCoin * (long) k;
        long answer = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (countAmounts(mid, coins) >= k) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }
}
