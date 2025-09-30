/**
 * Demonstration of algorithms for computing the prefix averages of an array.
 *
 * Exercise 2: Compare prefixAverage1 and prefixAverage2.
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class PrefixAverage {

    /**
     * Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j].
     */
    // Big-O: O(n^2)
    // Explanation: Outer loop runs n times, inner loop runs up to j times,
    // so total work = 1 + 2 + ... + n = n(n+1)/2 = Θ(n^2).
    public static double[] prefixAverage1(double[] x) {
        int n = x.length;
        double[] a = new double[n];    // filled with zeros by default
        for (int j = 0; j < n; j++) {
            double total = 0;            // compute x[0] + ... + x[j]
            for (int i = 0; i <= j; i++)
                total += x[i];
            a[j] = total / (j+1);        // record the average
        }
        return a;
    }

    /**
     * Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j].
     */
    // Big-O: O(n)
    // Explanation: Single loop, with a running prefix sum updated in O(1) per step.
    // Total runtime grows linearly with n.
    public static double[] prefixAverage2(double[] x) {
        int n = x.length;
        double[] a = new double[n];    // filled with zeros by default
        double total = 0;              // compute prefix sum as x[0] + x[1] + ...
        for (int j = 0; j < n; j++) {
            total += x[j];               // update prefix sum
            a[j] = total / (j+1);        // compute average in O(1)
        }
        return a;
    }

}



class PrefixAverageExperiment {

    // utility to generate test array
    public static double[] makeArray(int n) {
        double[] a = new double[n];
        Random rand = new Random(42);
        for (int i = 0; i < n; i++) {
            a[i] = rand.nextDouble() * 100;
        }
        return a;
    }

    // measure runtime in milliseconds
    public static long timeMillis(Runnable task) {
        long t0 = System.currentTimeMillis();
        task.run();
        return System.currentTimeMillis() - t0;
    }

    public static void main(String[] args) throws IOException {
        int[] sizes = {10, 100, 500, 1_000, 2_000, 5_000, 10_000, 20_000};

        System.out.printf("%-10s %-20s %-20s%n", "n", "prefixAverage1 (ms)", "prefixAverage2 (ms)");

        try (FileWriter csv = new FileWriter("prefix_average_times.csv")) {
            csv.write("n,prefixAverage1_ms,prefixAverage2_ms\n");

            for (int n : sizes) {
                double[] arr = makeArray(n);

                long t1 = timeMillis(() -> PrefixAverage.prefixAverage1(arr));
                long t2 = timeMillis(() -> PrefixAverage.prefixAverage2(arr));

                System.out.printf("%-10d %-20d %-20d%n", n, t1, t2);
                csv.write(n + "," + t1 + "," + t2 + "\n");
            }
        }

    }
}
