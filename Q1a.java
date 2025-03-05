public class Q1a {
    public int minMeasurements(int k, int n) {
        int[][] dp = new int[n + 1][k + 1];
        int m = 0;
        while (dp[m][k] < n) {
            m++;
            for (int i = 1; i <= k; i++) {
                dp[m][i] = dp[m - 1][i - 1] + dp[m - 1][i] + 1;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        Q1a solution = new Q1a();
        int k1 = 1, n1 = 2;
        System.out.println("Minimum measurements for k=" + k1 + ", n=" + n1 + ": " + solution.minMeasurements(k1, n1)); // Output:
                                                                                                                        // 2

        int k2 = 2, n2 = 6;
        System.out.println("Minimum measurements for k=" + k2 + ", n=" + n2 + ": " + solution.minMeasurements(k2, n2)); // Output:
                                                                                                                        // 3

        int k3 = 3, n3 = 14;
        System.out.println("Minimum measurements for k=" + k3 + ", n=" + n3 + ": " + solution.minMeasurements(k3, n3)); // Output:
                                                                                                                        // 4
    }
}

/*
 * Minimum measurements for k=1, n=2: 2
 * Minimum measurements for k=2, n=6: 3
 * Minimum measurements for k=3, n=14: 4
 */