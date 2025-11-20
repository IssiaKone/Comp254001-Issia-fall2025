import java.util.Arrays;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Demonstration of algorithms for testing element uniqueness.
 *
 * Exercise 3: Compare unique1 (quadratic) vs unique2 (n log n).
 */
class Uniqueness {

    /** Returns true if there are no duplicate elements in the array. */
    // Big-O: O(n^2)
    // Explanation: Two nested loops compare all pairs (j,k) with j<k.
    // Total comparisons = n(n-1)/2 = Θ(n^2).
    public static boolean unique1(int[] data) {
        int n = data.length;
        for (int j = 0; j < n - 1; j++)
            for (int k = j + 1; k < n; k++)
                if (data[j] == data[k])
                    return false;               // found duplicate pair
        return true;                      // all elements unique
    }

    /** Returns true if there are no duplicate elements in the array. */
    // Big-O: O(n log n)
    // Explanation: We copy and sort the array, which dominates the time (O(n log n)),
    // then do a single linear pass to check neighbors (O(n)).
    public static boolean unique2(int[] data) {
        int n = data.length;
        int[] temp = Arrays.copyOf(data, n); // make copy of data
        Arrays.sort(temp);                   // O(n log n)
        for (int j = 0; j < n - 1; j++)
            if (temp[j] == temp[j + 1])       // check neighboring entries
                return false;                    // found duplicate pair
        return true;                         // all elements unique
    }
}


/**
 * Exercise 3 driver:
 * Find largest n such that unique1 / unique2 finish in <= LIMIT_MS.
 * Uses exponential search + binary search, with simple timing.
 */
class UniquenessExperiment {

    // Build an array of size n with all-unique values (worst-case for unique1: no early exit)
    static int[] makeUniqueArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;
        return a;
    }

    // Measure elapsed ms for running 'task'
    static long timeMs(Runnable task) {
        long t0 = System.nanoTime();
        task.run();
        return (System.nanoTime() - t0) / 1_000_000L;
    }

    // Returns true if alg(array) completes within limitMs; prints a progress line
    static boolean completesWithin(int n, long limitMs, boolean useUnique1) {
        int[] A = makeUniqueArray(n);
        long t = timeMs(() -> {
            if (useUnique1) Uniqueness.unique1(A);
            else Uniqueness.unique2(A);
        });
        System.out.printf("  n=%s  -> %s in %d ms%n",
                pretty(n),
                (t <= limitMs ? "OK" : "TIMEOUT"),
                t);
        return t <= limitMs;
    }

    // Find max n that completes within limitMs using exponential then binary search
    static int maxNUnder(long limitMs, boolean useUnique1, int startGuess) {
        String name = useUnique1 ? "unique1 (O(n^2))" : "unique2 (O(n log n))";
        System.out.println("\nSearching max n for " + name + " under " + limitMs + " ms...");

        int lo = 1;
        int hi = Math.max(2, startGuess);

        // Exponential grow until we exceed limit
        while (completesWithin(hi, limitMs, useUnique1)) {
            lo = hi;
            hi = hi * 2;
            if (hi > 100_000_000) break; // safety cap
        }

        // Binary search in (lo, hi]
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2; // bias upward
            if (completesWithin(mid, limitMs, useUnique1)) {
                lo = mid;        // mid is OK, move up
            } else {
                hi = mid - 1;    // mid too big, move down
            }
        }
        return lo;
    }

    static String pretty(int n) {
        return NumberFormat.getNumberInstance(Locale.US).format(n);
    }

    public static void main(String[] args) {
        long LIMIT_MS = 60_000;   // 60 seconds
        int START_GUESS = 1_000;  // initial probe
        if (args.length >= 1) try { LIMIT_MS = Long.parseLong(args[0]); } catch (Exception ignored) {}
        if (args.length >= 2) try { START_GUESS = Integer.parseInt(args[1]); } catch (Exception ignored) {}

        // Warmup to stabilize JIT
        completesWithin(2_000, 1_000, true);
        completesWithin(2_000, 1_000, false);

        int max1 = maxNUnder(LIMIT_MS, true,  START_GUESS);
        System.out.println("=> Max n for unique1 within " + LIMIT_MS + " ms ≈ " + pretty(max1));

        int max2 = maxNUnder(LIMIT_MS, false, START_GUESS);
        System.out.println("=> Max n for unique2 within " + LIMIT_MS + " ms ≈ " + pretty(max2));

        System.out.println("\nNotes:");
        System.out.println("- We use arrays with all-unique values (worst case for unique1).");
        System.out.println("- Exact numbers vary by hardware/OS/JVM; the *relative* gap is what matters.");
    }
}
