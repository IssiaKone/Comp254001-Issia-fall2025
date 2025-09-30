/**
 * Code for end-of-chapter exercises on asymptotics.
 *
 * Exercise 1: Big-O characterization of example1–5
 */
class Exercises {

    /** Returns the sum of the integers in given array. */
    // Big-O: O(n)
    // Explanation: Single loop runs n times, each iteration does constant work.
    public static int example1(int[] arr) {
        int n = arr.length, total = 0;
        for (int j = 0; j < n; j++)       // loop from 0 to n-1
            total += arr[j];
        return total;
    }

    /** Returns the sum of the integers with even index in given array. */
    // Big-O: O(n)
    // Explanation: Loop visits ~n/2 elements (j += 2), which is still Θ(n).
    public static int example2(int[] arr) {
        int n = arr.length, total = 0;
        for (int j = 0; j < n; j += 2)    // note the increment of 2
            total += arr[j];
        return total;
    }

    /** Returns the sum of the prefix sums of given array. */
    // Big-O: O(n^2)
    // Explanation: Outer loop runs n times; inner loop runs up to j+1 times.
    // Total operations = 1 + 2 + ... + n = n(n+1)/2 = Θ(n^2).
    public static int example3(int[] arr) {
        int n = arr.length, total = 0;
        for (int j = 0; j < n; j++)       // loop from 0 to n-1
            for (int k = 0; k <= j; k++)    // loop from 0 to j
                total += arr[j];
        return total;
    }

    /** Returns the sum of the prefix sums of given array. */
    // Big-O: O(n)
    // Explanation: Single loop over n elements, each iteration updates
    // prefix and adds it to total. Constant extra work per step.
    public static int example4(int[] arr) {
        int n = arr.length, prefix = 0, total = 0;
        for (int j = 0; j < n; j++) {     // loop from 0 to n-1
            prefix += arr[j];
            total += prefix;
        }
        return total;
    }

    /** Returns the number of times second array stores sum of prefix sums from first. */
    // Big-O: O(n^3)
    // Explanation: Outer loop runs n times. Inner nested loops perform
    // 1 + 2 + ... + n = Θ(n^2) work per iteration. Total = n × Θ(n^2) = Θ(n^3).
    public static int example5(int[] first, int[] second) { // assume equal-length arrays
        int n = first.length, count = 0;
        for (int i = 0; i < n; i++) {     // loop from 0 to n-1
            int total = 0;
            for (int j = 0; j < n; j++)     // loop from 0 to n-1
                for (int k = 0; k <= j; k++)  // loop from 0 to j
                    total += first[k];
            if (second[i] == total) count++;
        }
        return count;
    }
}
