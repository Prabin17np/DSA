/*
 Problem: Find the closest pair of points based on Manhattan distance. If multiple pairs have the same distance, choose the lexicographically smallest pair.

Approach:

Iterate through all pairs of points (i, j) where i < j.

Calculate the Manhattan distance between each pair.

Track the pair with the smallest distance.

If distances are equal, choose the pair with the smallest lexicographical order.*/

import java.util.Arrays;

public class Q2b {
    public static int[] findClosestPair(int[] x_coords, int[] y_coords) {
        int n = x_coords.length;
        int minDistance = Integer.MAX_VALUE; // Initialize minimum distance to a large value
        int[] result = new int[2]; // To store the result indices

        // Iterate through all possible pairs of points
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Calculate Manhattan distance
                int distance = Math.abs(x_coords[i] - x_coords[j]) + Math.abs(y_coords[i] - y_coords[j]);

                // If the current distance is smaller than the minimum distance found so far,
                // update the minimum distance and the result indices
                if (distance < minDistance) {
                    minDistance = distance;
                    result[0] = i;
                    result[1] = j;
                }
                // If the distance is equal to the minimum distance, choose the
                // lexicographically smaller pair
                else if (distance == minDistance) {
                    if (i < result[0] || (i == result[0] && j < result[1])) {
                        result[0] = i;
                        result[1] = j;
                    }
                }
            }
        }

        // Return the indices of the closest pair
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        int[] x_coords = { 1, 2, 3, 2, 4 };
        int[] y_coords = { 2, 3, 1, 2, 3 };

        int[] result = findClosestPair(x_coords, y_coords);
        System.out.println(Arrays.toString(result)); // Output: [0, 3]
    }
}

// Output
// [0, 3]