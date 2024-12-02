package ps_question;

import java.util.*;

public class AdjecentCountSolver {

    // Solution 1: Brute Force Approach
    public static boolean hasAdjacentSum(int[] arr, int target) {
        // Time Complexity: O(n^2)
        // Space Complexity: O(1)
        for (int i = 0; i < arr.length; i++) {
            int currentSum = 0;
            for (int j = i; j < arr.length; j++) {
                currentSum += arr[j];
                if (currentSum == target) {
                    return true;
                }
                if (currentSum > target) {
                    break;
                }
            }
        }
        return false;
    }

    // Solution 2: Sliding Window Approach
    public static boolean hasAdjacentSumOptimized(int[] arr, int target) {
        // Time Complexity: O(n)
        // Space Complexity: O(1)
        int currentSum = 0;
        int start = 0;

        for (int end = 0; end < arr.length; end++) {
            // Add the current element to the sum
            currentSum += arr[end];

            // Shrink the window from the start if sum exceeds target
            while (currentSum > target && start <= end) {
                currentSum -= arr[start];
                start++;
            }

            // Check if we found the target sum
            if (currentSum == target) {
                return true;
            }
        }

        return false;
    }

    // Solution 3: Using Prefix Sum and HashSet
    public static boolean hasAdjacentSumHashSet(int[] arr, int target) {
        // Time Complexity: O(n)
        // Space Complexity: O(n)
        Set<Integer> prefixSums = new HashSet<>();
        int currentSum = 0;

        for (int num : arr) {
            currentSum += num;

            // Check if the current sum minus target exists in previous prefix sums
            if (prefixSums.contains(currentSum - target)) { // hashset.contains O(1) Operation
                return true;
            }

            prefixSums.add(currentSum);
        }

        return false;
    }

    // Main method to demonstrate the solutions
    public static void main(String[] args) {
        int[] arr = {5, 3, 3, 5, 2, 4, 1};
        int target = 10;

        System.out.println("Brute Force Solution: " + hasAdjacentSum(arr, target));
        System.out.println("Sliding Window Solution: " + hasAdjacentSumOptimized(arr, target));
        System.out.println("HashSet Solution: " + hasAdjacentSumHashSet(arr, target));
    }
}